
package controlador;

import dao.LibroDAO;
import dao.EstudianteDAO;
import dao.PrestamoDAO;
import modelo.Libro;
import modelo.Estudiante;
import modelo.Prestamo;
import java.util.List;

public class BibliotecaController {

    private final LibroDAO libroDAO = new LibroDAO();
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();
    private final PrestamoDAO prestamoDAO = new PrestamoDAO();

    public List<Libro> obtenerLibros() {
        return libroDAO.listar();
    }

    public List<Estudiante> obtenerEstudiantes() {
        return estudianteDAO.listar();
    }

    public List<Prestamo> obtenerPrestamos() {
        return prestamoDAO.listar();
    }

    public boolean registrarPrestamo(Prestamo prestamo) {
        return prestamoDAO.registrarPrestamo(prestamo);
    }

    public boolean devolverPrestamo(int idPrestamo) {
        return prestamoDAO.devolverPrestamo(idPrestamo);
    }
}
