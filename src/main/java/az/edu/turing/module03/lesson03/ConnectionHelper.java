package az.edu.turing.module03.lesson03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    final static String url = "jdbc:postgresql://localhost:5433/test2";
    final static String user = "postgres";
    final static String password = "myPass";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Connection failed to connect to database.");
            throw new RuntimeException(e);
        }

        return connection;
    }
}