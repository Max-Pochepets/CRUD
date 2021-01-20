package crud.util;

import crud.lib.DataBaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static void clearTable() {
        String fkchecksOff = "SET FOREIGN_KEY_CHECKS = 0";
        String truncate1 = "TRUNCATE TABLE cars";
        String truncate2 = "TRUNCATE TABLE cars_drivers";
        String truncate3 = "TRUNCATE TABLE drivers";
        String truncate4 = "TRUNCATE TABLE manufacturers";
        String fkchecksOn = "SET FOREIGN_KEY_CHECKS = 1";
        try (Connection connection = getConnection()) {
            connection.createStatement().executeUpdate(fkchecksOff);
            connection.createStatement().executeUpdate(truncate1);
            connection.createStatement().executeUpdate(truncate2);
            connection.createStatement().executeUpdate(truncate3);
            connection.createStatement().executeUpdate(truncate4);
            connection.createStatement().executeUpdate(fkchecksOn);
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
