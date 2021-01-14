package crud.util;

import crud.lib.DataBaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static void clearTable(String table) {
        String clear = "TRUNCATE TABLE " + table;
        String resetId = "ALTER TABLE " + table + " AUTO_INCREMENT = 1";
        try (Connection connection = getConnection()) {
            connection.createStatement().executeUpdate(clear);
            connection.createStatement().executeUpdate(resetId);
        } catch (SQLException throwables) {
            throw new DataBaseException("Couldn't establish connection to MySQL server. ",
                    throwables);
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
