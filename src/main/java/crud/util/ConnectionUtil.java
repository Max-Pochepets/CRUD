package crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static void clearTable(String table) {
        String query = "DELETE FROM " + table;
        try (Connection connection = getConnection()) {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException throwables) {
            throw new RuntimeException("Couldn't establish connection to MySQL server. "
                    + throwables);
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties dbProperties = new Properties();
        dbProperties.setProperty("user", "root");
        dbProperties.setProperty("password", "1234");
        String url = "jdbc:mysql://localhost:3306/taxi?serverTimezone=UTC";
        return DriverManager.getConnection(url, dbProperties);
    }
}
