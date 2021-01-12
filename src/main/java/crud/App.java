package crud;

import crud.lib.Injector;
import crud.model.Car;
import crud.model.Driver;
import crud.model.Manufacturer;
import crud.service.abstraction.CarService;
import crud.service.abstraction.DriverService;
import crud.service.abstraction.ManufacturerService;

public class App {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final Manufacturer MANUFACTURER = new Manufacturer("Tesla", "USA");
    private static final Manufacturer MANUFACTURER_2 = new Manufacturer("Audi", "Germany");
    private static final Manufacturer MANUFACTURER_3 = new Manufacturer("Mercedes", "Germany");
    private static final Car CAR = new Car("Y", MANUFACTURER);
    private static final Car CAR_2 = new Car("RS7", MANUFACTURER_2);
    private static final Car CAR_3 = new Car("E63 S", MANUFACTURER_3);
    private static final Driver DRIVER = new Driver("Ed", "123qwe222113");
    private static final Driver DRIVER_2 = new Driver("John", "AAAEWWW222JHDSF8899");
    private static final Driver DRIVER_3 = new Driver("BOB", "IEH709898SDF7689SDF231");

    public static void main(String[] args) {
        ManufacturerService MANUFACTURER_SERVICE
                = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
        CarService CAR_SERVICE = (CarService) INJECTOR.getInstance(CarService.class);
        DriverService DRIVER_SERVICE = (DriverService) INJECTOR.getInstance(DriverService.class);

        MANUFACTURER_SERVICE.create(MANUFACTURER);
        MANUFACTURER_SERVICE.create(MANUFACTURER_2);
        MANUFACTURER_SERVICE.create(MANUFACTURER_3);
        System.out.println("Displaying all manufacturers: " + MANUFACTURER_SERVICE.getAll());

        CAR_SERVICE.create(CAR);
        CAR_SERVICE.create(CAR_2);
        CAR_SERVICE.create(CAR_3);
        System.out.println("Displaying all cars: " + CAR_SERVICE.getAll());

        DRIVER_SERVICE.create(DRIVER);
        DRIVER_SERVICE.create(DRIVER_2);
        DRIVER_SERVICE.create(DRIVER_3);
        System.out.println("Displaying all drivers: " + DRIVER_SERVICE.getAll());

        CAR_SERVICE.addDriverToCar(DRIVER, CAR);
        CAR_SERVICE.addDriverToCar(DRIVER_2, CAR);
        CAR_SERVICE.addDriverToCar(DRIVER_3, CAR);
        CAR_SERVICE.addDriverToCar(DRIVER, CAR_2);
        CAR_SERVICE.addDriverToCar(DRIVER_2, CAR_2);
        CAR_SERVICE.addDriverToCar(DRIVER, CAR_3);
        System.out.println("Displaying all cars after assigning drivers: " + CAR_SERVICE.getAll());

        System.out.println("Cars driven by driver with ID 2" + CAR_SERVICE.getAllByDriver(2L));

        for (long i = 1; i <= 3; i++) {
            System.out.println("Manufacturer with ID " + i + ": " + MANUFACTURER_SERVICE.get(i));
            System.out.println("Car with ID " + i + ": " + CAR_SERVICE.get(i));
            System.out.println("Driver with ID " + i + ": " + DRIVER_SERVICE.get(i));
        }

        for (long i = 3; i > 0; i--) {
            MANUFACTURER_SERVICE.delete(i);
            CAR_SERVICE.delete(i);
            DRIVER_SERVICE.delete(i);
            System.out.println("Manufacturers after deleting by ID " + i + ": " + MANUFACTURER_SERVICE.getAll());
            System.out.println("Cars after deleting by ID " + i + ": " + CAR_SERVICE.getAll());
            System.out.println("Manufacturers after deleting by ID " + i + ": " + DRIVER_SERVICE.getAll());
        }
    }
}
