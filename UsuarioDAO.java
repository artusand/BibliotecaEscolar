
package dao;

import conexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;
import modelo.Bibliotecario;
import modelo.Estudiante;

public class UsuarioDAO {

    public Usuario autenticar(String correo, String contraseña) {

        String sql = "SELECT * FROM usuarios "
                + "WHERE correo=? AND contraseña=?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String rut = rs.getString("rut");
                    String email = rs.getString("correo");
                    String rol = rs.getString("rol");

                    if ("bibliotecario".equals(rol)) {
                        return new Bibliotecario(
                                id, nombre, rut, email);
                    }

                    if ("estudiante".equals(rol)) {
                        return new Estudiante(
                                id, nombre, rut, email, "");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al autenticar: " + e.getMessage());
        }

        return null;
    }
}
