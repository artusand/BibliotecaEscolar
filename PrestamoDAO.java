
package dao;

import conexion.DatabaseConnection;
import modelo.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    // Registrar préstamo y descontar stock
    public synchronized boolean registrarPrestamo(Prestamo prestamo) {

        String consultarStock =
                "SELECT stock FROM libros WHERE id=? FOR UPDATE";

        String insertar =
                "INSERT INTO prestamos "
                + "(id_estudiante, id_libro, fecha_prestamo, "
                + "fecha_devolucion, devuelto) "
                + "VALUES (?, ?, ?, ?, ?)";

        String actualizarStock =
                "UPDATE libros SET stock=stock-1 WHERE id=?";

        try (Connection con =
                DatabaseConnection.getInstance().getConnection()) {

            con.setAutoCommit(false);

            try {
                // Bloquear el libro mientras se realiza el préstamo
                try (PreparedStatement ps =
                        con.prepareStatement(consultarStock)) {

                    ps.setInt(1, prestamo.getIdLibro());

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next() || rs.getInt("stock") <= 0) {
                            con.rollback();
                            return false;
                        }
                    }
                }

                // Registrar préstamo
                try (PreparedStatement ps =
                        con.prepareStatement(insertar)) {

                    ps.setInt(1, prestamo.getIdEstudiante());
                    ps.setInt(2, prestamo.getIdLibro());
                    ps.setDate(3, Date.valueOf(
                            prestamo.getFechaPrestamo()));
                    ps.setDate(4, Date.valueOf(
                            prestamo.getFechaDevolucion()));
                    ps.setBoolean(5, false);

                    ps.executeUpdate();
                }

                // Descontar stock
                try (PreparedStatement ps =
                        con.prepareStatement(actualizarStock)) {

                    ps.setInt(1, prestamo.getIdLibro());
                    ps.executeUpdate();
                }

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                System.out.println(
                        "Error al registrar préstamo: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error de conexión: " + e.getMessage());
            return false;
        }
    }

    // Registrar devolución y recuperar stock
    public synchronized boolean devolverPrestamo(int idPrestamo) {

        String consultar =
                "SELECT id_libro, devuelto FROM prestamos "
                + "WHERE id=? FOR UPDATE";

        String devolver =
                "UPDATE prestamos SET devuelto=TRUE WHERE id=?";

        String devolverStock =
                "UPDATE libros SET stock=stock+1 WHERE id=?";

        try (Connection con =
                DatabaseConnection.getInstance().getConnection()) {

            con.setAutoCommit(false);

            try {
                int idLibro;

                try (PreparedStatement ps =
                        con.prepareStatement(consultar)) {

                    ps.setInt(1, idPrestamo);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next() || rs.getBoolean("devuelto")) {
                            con.rollback();
                            return false;
                        }

                        idLibro = rs.getInt("id_libro");
                    }
                }

                try (PreparedStatement ps =
                        con.prepareStatement(devolver)) {

                    ps.setInt(1, idPrestamo);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps =
                        con.prepareStatement(devolverStock)) {

                    ps.setInt(1, idLibro);
                    ps.executeUpdate();
                }

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                System.out.println(
                        "Error al devolver préstamo: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error de conexión: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los préstamos
    public List<Prestamo> listar() {

        List<Prestamo> prestamos = new ArrayList<>();

        String sql = "SELECT * FROM prestamos";

        try (Connection con =
                DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo").toLocalDate(),
                        rs.getDate("fecha_devolucion").toLocalDate(),
                        rs.getBoolean("devuelto")
                );

                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al listar préstamos: " + e.getMessage());
        }

        return prestamos;
    }
}
