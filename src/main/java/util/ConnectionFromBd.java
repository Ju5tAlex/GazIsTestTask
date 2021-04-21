package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFromBd {
    private static final String DB_URL2 = "jdbc:h2:mem:users";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed())
                return connection;
            else
                connection = DriverManager.getConnection(DB_URL2, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
