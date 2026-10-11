package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instancia;

    private static final String URL =
            "jdbc:mysql://localhost:3306/biblioteca";

    private static final String USER = "biblioteca_user";

    private static final String PASSWORD =
            System.getProperty("BIBLIOTECA_DB_PASSWORD");

    private DatabaseConnection() {
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instancia == null) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}