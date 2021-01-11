package crud.storage;

import crud.model.Car;
import crud.model.Driver;
import crud.model.Manufacturer;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static Map<Long, Car> carsStorage = new HashMap<>();
    public static Map<Long, Driver> driversStorage = new HashMap<>();
    public static Map<Long, Manufacturer> manufacturersStorage = new HashMap<>();
    private static Long carId = 0L;
    private static Long driverId = 0L;
    private static Long manufacturerId = 0L;

    public static void addCar(Car car) {
        carId++;
        car.setId(carId);
        carsStorage.put(car.getId(), car);
    }

    public static void addDriver(Driver driver) {
        driverId++;
        driver.setId(driverId);
        driversStorage.put(driver.getId(), driver);
    }

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        manufacturersStorage.put(manufacturer.getId(), manufacturer);
    }
}
