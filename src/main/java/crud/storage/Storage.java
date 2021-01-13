package crud.storage;

import crud.model.Car;
import crud.model.Driver;
import crud.model.Manufacturer;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static Map<Long, Car> cars = new HashMap<>();
    public static Map<Long, Driver> drivers = new HashMap<>();
    public static Map<Long, Manufacturer> manufacturers = new HashMap<>();
    private static Long carId = 0L;
    private static Long driverId = 0L;
    private static Long manufacturerId = 0L;

    public static Car addCar(Car car) {
        car.setId(++carId);
        cars.put(carId, car);
        return car;
    }

    public static Driver addDriver(Driver driver) {
        driver.setId(++driverId);
        drivers.put(driverId, driver);
        return driver;
    }

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        manufacturers.put(manufacturerId, manufacturer);
        return manufacturer;
    }
}
