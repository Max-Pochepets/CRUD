package crud;

import crud.lib.Injector;
import crud.model.Manufacturer;
import crud.service.abstraction.ManufacturerService;
import crud.util.ConnectionUtil;

public class App {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final Manufacturer MANUFACTURER = new Manufacturer("Tesla", "USA");
    private static final Manufacturer MANUFACTURER_2 = new Manufacturer("Audi", "Germany");
    private static final Manufacturer MANUFACTURER_3 = new Manufacturer("Mercedes", "Germany");
    private static final String MANUFACTURERS = "manufacturers";
    /*private static final Car CAR = new Car("Y", MANUFACTURER);
    private static final Car CAR_2 = new Car("RS7", MANUFACTURER_2);
    private static final Car CAR_3 = new Car("E63 S", MANUFACTURER_3);
    private static final Driver DRIVER = new Driver("Ed", "123qwe222113");
    private static final Driver DRIVER_2 = new Driver("John", "AAAEWWW222JHDSF8899");
    private static final Driver DRIVER_3 = new Driver("BOB", "IEH709898SDF7689SDF231");

    public static void main(String[] args) {
        final ManufacturerService manufacturerService
                = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
        final CarService carService
                = (CarService) INJECTOR.getInstance(CarService.class);
        final DriverService driverService
                = (DriverService) INJECTOR.getInstance(DriverService.class);

        manufacturerService.create(MANUFACTURER);
        manufacturerService.create(MANUFACTURER_2);
        manufacturerService.create(MANUFACTURER_3);
        System.out.println("Displaying all manufacturers: " + manufacturerService.getAll());

        carService.create(CAR);
        carService.create(CAR_2);
        carService.create(CAR_3);
        System.out.println("Displaying all cars: " + carService.getAll());

        driverService.create(DRIVER);
        driverService.create(DRIVER_2);
        driverService.create(DRIVER_3);
        System.out.println("Displaying all drivers: " + driverService.getAll());

        carService.addDriverToCar(DRIVER, CAR);
        carService.addDriverToCar(DRIVER_2, CAR);
        carService.addDriverToCar(DRIVER_3, CAR);
        carService.addDriverToCar(DRIVER, CAR_2);
        carService.addDriverToCar(DRIVER_2, CAR_2);
        carService.addDriverToCar(DRIVER, CAR_3);
        System.out.println("Displaying all cars after assigning drivers: " + carService.getAll());

        System.out.println("Cars driven by driver with ID 2" + carService.getAllByDriver(2L));

        for (long i = 1; i <= 3; i++) {
            System.out.println("Manufacturer with ID " + i + ": " + manufacturerService.get(i));
            System.out.println("Car with ID " + i + ": " + carService.get(i));
            System.out.println("Driver with ID " + i + ": " + driverService.get(i));
        }

        for (long i = 3; i > 0; i--) {
            manufacturerService.delete(i);
            carService.delete(i);
            driverService.delete(i);
            System.out.println("Manufacturers after deleting by ID " + i + ": "
                    + manufacturerService.getAll());
            System.out.println("Cars after deleting by ID " + i + ": "
                    + carService.getAll());
            System.out.println("Manufacturers after deleting by ID " + i + ": "
                    + driverService.getAll());
        }
    }*/

    public static void main(String[] args) {
        ConnectionUtil.clearTable(MANUFACTURERS);
        ManufacturerService manufacturerService
                = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
        manufacturerService.create(MANUFACTURER);
        manufacturerService.create(MANUFACTURER_2);
        manufacturerService.create(MANUFACTURER_3);
        System.out.println(manufacturerService.getAll());
        System.out.println(manufacturerService.get(26L));
        MANUFACTURER.setName("Ford");
        manufacturerService.update(MANUFACTURER);
        manufacturerService.delete(27L);
        System.out.println(manufacturerService.getAll());
    }
}
