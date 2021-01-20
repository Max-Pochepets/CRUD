package crud.dao.impl;

import crud.dao.abstraction.DriverDao;
import crud.lib.DaoImpl;
import crud.lib.DataBaseException;
import crud.model.Driver;
import crud.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DaoImpl
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers (driver_name, driver_license_number) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            setUpdate(statement, driver).executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
                driver.setId(set.getObject(1, Long.class));
            }
            return driver;
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't create insert "
                    + driver + " into driversDB. ", throwable);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT driver_id, driver_name, driver_license_number FROM drivers "
                + "WHERE driver_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            Driver driver = null;
            if (set.next()) {
                driver = setDriver(set);
            }
            return Optional.ofNullable(driver);
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't get driver by id " + id, throwable);
        }
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT * FROM drivers WHERE deleted = FALSE";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                drivers.add(setDriver(set));
            }
            return drivers;
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't get a list of drivers from driversDB.",
                    throwable);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers "
                + "SET driver_name = ?, driver_license_number = ? "
                + "WHERE driver_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = setUpdate(connection.prepareStatement(query), driver)) {
            statement.setLong(3, driver.getId());
            statement.executeUpdate();
            return driver;
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't update " + driver + " in driversDB.", throwable);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET deleted = TRUE WHERE driver_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't delete driver with id " + id, throwable);
        }
    }

    static Driver setDriver(ResultSet resultSet) throws SQLException {
        Long newId = resultSet.getObject("driver_id", Long.class);
        String name = resultSet.getString("driver_name");
        String licenseNumber = resultSet.getString("driver_license_number");
        Driver driver = new Driver(name, licenseNumber);
        driver.setId(newId);
        return driver;
    }

    private PreparedStatement setUpdate(PreparedStatement statement,
                                        Driver driver) throws SQLException {
        statement.setString(1, driver.getName());
        statement.setString(2, driver.getLicenceNumber());
        return statement;
    }
}
