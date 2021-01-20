package crud.dao.impl;

import static crud.dao.impl.DriverDaoImpl.setDriver;
import static crud.dao.impl.ManufacturerDaoImpl.setManufacturer;

import crud.dao.abstraction.CarDao;
import crud.lib.DaoImpl;
import crud.lib.DataBaseException;
import crud.model.Car;
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
public class CarDaoImpl implements CarDao {
    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT * FROM cars c "
                + "LEFT JOIN manufacturers m ON m.manufacturer_id = c.manufacturer_id "
                + "LEFT JOIN cars_drivers cd ON cd.cars_id = c.car_id "
                + "LEFT JOIN drivers d ON cd.driver_id = d.driver_id "
                + "WHERE d.driver_id = ? AND c.deleted = FALSE AND d.deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            List<Car> cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = setCar(resultSet);
                getAllDriversByCar(connection, car);
                cars.add(car);
            }
            return cars;
        } catch (SQLException throwable) {
            throw new DataBaseException("Could not get a list of cars by driver id "
                    + driverId, throwable);
        }
    }

    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (manufacturer_id, car_model) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = setUpdate(connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS), car)) {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't insert " + car + " into carsDB.", throwable);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT c.car_id, m.manufacturer_id, c.car_model, "
                    + "m.manufacturer_name, m.manufacturer_country FROM cars c "
                + "JOIN manufacturers m ON m.manufacturer_id = c.manufacturer_id "
                + "WHERE c.car_id = ? AND c.deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = setCar(resultSet);
                getAllDriversByCar(connection, car);
            }
            return Optional.ofNullable(car);
        } catch (SQLException throwable) {
            throw new DataBaseException("Could not get car by id " + id, throwable);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM cars c "
                + "JOIN manufacturers m ON m.manufacturer_id = c.manufacturer_id "
                + "WHERE c.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = setCar(resultSet);
                getAllDriversByCar(connection, car);
                cars.add(car);
            }
            return cars;
        } catch (SQLException throwable) {
            throw new DataBaseException("Could not get a list of cars from DB.", throwable);
        }
    }

    @Override
    public Car update(Car car) {
        String updateCar = "UPDATE cars "
                + "SET car_model = ?, manufacturer_id = ? "
                + "WHERE car_id = ? AND deleted = FALSE";
        String deleteRelations = "DELETE FROM cars_drivers "
                + "WHERE cars_id = ?";
        String updateDrivers = "INSERT INTO cars_drivers (driver_id, cars_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updateCarStatement = connection.prepareStatement(updateCar);
                PreparedStatement deleteRelationsStatement
                        = connection.prepareStatement(deleteRelations);
                PreparedStatement updateDriversStatement
                        = connection.prepareStatement(updateDrivers)) {
            updateCarStatement.setString(1, car.getModel());
            updateCarStatement.setLong(2, car.getManufacturer().getId());
            updateCarStatement.setLong(3, car.getId());
            updateCarStatement.executeUpdate();
            deleteRelationsStatement.setLong(1, car.getId());
            deleteRelationsStatement.executeUpdate();
            List<Driver> drivers = car.getDrivers();
            for (Driver driver :drivers) {
                updateDriversStatement.setLong(1, driver.getId());
                updateDriversStatement.setLong(2, car.getId());
                updateDriversStatement.executeUpdate();
            }
            return car;
        } catch (SQLException throwable) {
            throw new DataBaseException("Could not update " + car + " in DB", throwable);
        }
    }

    @Override
    public boolean delete(Long id) {
        String updateQuery = "UPDATE cars "
                + "SET deleted = TRUE WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DataBaseException("Couldn't delete car by id " + id, throwable);
        }
    }

    private Car setCar(ResultSet set) throws SQLException {
        Long carId = set.getObject(1, Long.class);
        String carModel = set.getString("car_model");
        Car car = new Car(carModel, setManufacturer(set));
        car.setId(carId);
        return car;
    }

    private void getAllDriversByCar(Connection connection, Car car) throws SQLException {
        String getDriversQuery = "SELECT d.driver_id, d.driver_name, d.driver_license_number "
                + "FROM cars_drivers cd "
                + "JOIN drivers d on d.driver_id = cd.driver_id "
                + "WHERE cd.cars_id = ? AND d.deleted = FALSE";
        try (PreparedStatement statement2 = connection.prepareStatement(getDriversQuery)) {
            statement2.setLong(1, car.getId());
            ResultSet resultSet = statement2.executeQuery();
            while (resultSet.next()) {
                car.getDrivers().add(setDriver(resultSet));
            }
        }
    }

    private PreparedStatement setUpdate(PreparedStatement statement,
                                        Car car) throws SQLException {
        statement.setLong(1, car.getManufacturer().getId());
        statement.setString(2, car.getModel());
        return statement;
    }
}
