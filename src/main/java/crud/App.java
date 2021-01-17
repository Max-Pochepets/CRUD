package crud;

import crud.lib.Injector;
import crud.model.Car;
import crud.model.Driver;
import crud.model.Manufacturer;
import crud.service.abstraction.CarService;
import crud.service.abstraction.DriverService;
import crud.service.abstraction.ManufacturerService;
import crud.util.ConnectionUtil;

public class App {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final Manufacturer MANUFACTURER = new Manufacturer("Tesla", "USA");
    private static final Manufacturer MANUFACTURER_2 = new Manufacturer("Audi", "Germany");
    private static final Manufacturer MANUFACTURER_3 = new Manufacturer("Mercedes", "Germany");
    private static final Driver DRIVER = new Driver("Ed", "123qwe222113");
    private static final Driver DRIVER_2 = new Driver("John", "AAAEWWW222JHDSF8899");
    private static final Driver DRIVER_3 = new Driver("BOB", "IEH709898SDF7689SDF231");
    private static final Car CAR = new Car("Y", MANUFACTURER);
    private static final Car CAR_2 = new Car("RS7", MANUFACTURER_2);
    private static final Car CAR_3 = new Car("E63 S", MANUFACTURER_3);

    public static void main(String[] args) {
        ConnectionUtil.clearTable();
        final CarService carService
                = (CarService) INJECTOR.getInstance(CarService.class);
        final DriverService driverService
                = (DriverService) INJECTOR.getInstance(DriverService.class);
        final ManufacturerService manufacturerService
                = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
        manufacturerService.create(MANUFACTURER);
        manufacturerService.create(MANUFACTURER_2);
        manufacturerService.create(MANUFACTURER_3);
        carService.create(CAR);
        carService.create(CAR_2);
        carService.create(CAR_3);
        driverService.create(DRIVER);
        driverService.create(DRIVER_2);
        driverService.create(DRIVER_3);
        carService.addDriverToCar(DRIVER_2, CAR);
        carService.addDriverToCar(DRIVER, CAR);
        carService.addDriverToCar(DRIVER, CAR_2);
        carService.addDriverToCar(DRIVER_3, CAR_2);
        carService.addDriverToCar(DRIVER_2, CAR_3);
        carService.addDriverToCar(DRIVER_3, CAR_3);
        System.out.println(carService.getAll());
        System.out.println(carService.get(1L));
        carService.removeDriverFromCar(DRIVER_2, CAR);
        System.out.println(carService.get(1L));
        System.out.println(carService.getAllByDriver(2L));
        System.out.println(driverService.getAll());
        System.out.println(driverService.get(2L));
        System.out.println(manufacturerService.getAll());
        System.out.println(manufacturerService.get(2L));
        DRIVER.setName("Kent");
        MANUFACTURER.setName("Ford");
        driverService.update(DRIVER);
        manufacturerService.update(MANUFACTURER);
        driverService.delete(2L);
        manufacturerService.delete(2L);
        System.out.println(driverService.getAll());
        System.out.println(manufacturerService.getAll());
    }
}
