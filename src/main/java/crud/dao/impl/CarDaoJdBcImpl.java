package crud.dao.impl;

import static crud.dao.impl.DriverDaoJdBcImpl.setDriver;
import static crud.dao.impl.ManufacturerDaoJdBcImpl.setManufacturer;

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
public class CarDaoJdBcImpl implements CarDao {
    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT c.car_id FROM cars_drivers cd "
                + "JOIN cars c on c.car_id = cd.cars_id "
                + "WHERE cd.driver_id = ? AND c.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Long carId = set.getObject(1, Long.class);
                cars.add(get(carId).get());
            }
            return cars;
        } catch (SQLException throwables) {
            throw new DataBaseException("Could not get a list of cars by driver id "
                    + driverId, throwables);
        }
    }

    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (manufacturer_id, car_model) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = setUpdate(connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS), car)) {
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
                car.setId(set.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException throwables) {
            throw new DataBaseException("Couldn't insert " + car + " into carsDB.", throwables);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT c.car_id, m.manufacturer_id, c.car_model, "
                    + "m.manufacturer_name, m.manufacturer_country FROM cars c "
                + "JOIN manufacturers m ON m.manufacturer_id = c.manufacturer_id "
                + "WHERE c.car_id = ? AND c.deleted = FALSE";
        String getDriversQuery = "SELECT d.driver_id, d.driver_name, d.driver_license_number "
                    + "FROM cars c "
                + "JOIN cars_drivers cd on c.car_id = cd.cars_id "
                + "JOIN drivers d on d.driver_id = cd.driver_id "
                + "WHERE c.car_id = ? AND c.deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            Car car = null;
            if (set.next()) {
                car = setCar(set);
                PreparedStatement statement2 = connection.prepareStatement(getDriversQuery);
                statement2.setLong(1, id);
                ResultSet set2 = statement2.executeQuery();
                while (set2.next()) {
                    car.getDrivers().add(setDriver(set2));
                }
                statement2.close();
            }
            return Optional.ofNullable(car);
        } catch (SQLException throwables) {
            throw new DataBaseException("Could not get car by id " + id, throwables);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT car_id FROM cars "
                + "WHERE deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Long carId = set.getObject(1, Long.class);
                cars.add(get(carId).get());
            }
            return cars;
        } catch (SQLException throwables) {
            throw new DataBaseException("Could not get a list of cars from DB.", throwables);
        }
    }

    @Override
    public Car update(Car car) {
        String updateCar = "UPDATE cars "
                + "SET car_model = ?, manufacturer_id = ? "
                + "WHERE car_id = ? AND deleted = FALSE";
        String deleteRelations = "DELETE cd FROM cars_drivers cd "
                + "JOIN cars c on c.car_id = cd.cars_id "
                + "WHERE cars_id = ? AND c.deleted = FALSE";
        String updateDrivers = "INSERT INTO cars_drivers (driver_id, cars_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateCar);
                PreparedStatement statement2 = connection.prepareStatement(deleteRelations);
                PreparedStatement statement3 = connection.prepareStatement(updateDrivers)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.setLong(3, car.getId());
            statement.executeUpdate();
            statement2.setLong(1, car.getId());
            statement2.executeUpdate();
            List<Driver> drivers = car.getDrivers();
            for (Driver driver :drivers) {
                statement3.setLong(1, driver.getId());
                statement3.setLong(2, car.getId());
                statement3.executeUpdate();
            }
            return car;
        } catch (SQLException throwables) {
            throw new DataBaseException("Could not update " + car + " in DB", throwables);
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
        } catch (SQLException throwables) {
            throw new DataBaseException("Couldn't delete car by id " + id, throwables);
        }
    }

    private Car setCar(ResultSet set) throws SQLException {
        Long carId = set.getObject(1, Long.class);
        String carModel = set.getString("car_model");
        Car car = new Car(carModel, setManufacturer(set));
        car.setId(carId);
        return car;
    }

    private PreparedStatement setUpdate(PreparedStatement statement,
                                        Car car) throws SQLException {
        statement.setLong(1, car.getManufacturer().getId());
        statement.setString(2, car.getModel());
        return statement;
    }
}
