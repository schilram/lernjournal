package ch.zhaw.schilram.lernjournal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author schilram
 */
public class JdbcConnection {

    private static final String USERNAME = "lernjournal";
    private static final String PASSWORD = "lernjournal";
    private static final String DB = "jdbc:postgresql://localhost:5432/lernjournal";

    private Connection connection;


    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(final Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
