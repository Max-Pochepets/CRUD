package crud.controller;

import crud.lib.Injector;
import crud.model.Car;
import crud.model.Driver;
import crud.model.Manufacturer;
import crud.service.abstraction.CarService;
import crud.service.abstraction.DriverService;
import crud.service.abstraction.ManufacturerService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final Manufacturer manufacturer
            = new Manufacturer("Tesla", "USA");
    private final Manufacturer manufacturer2
            = new Manufacturer("Audi", "Germany");
    private final Manufacturer manufacturer3
            = new Manufacturer("Mercedes", "Germany");
    private final Car car = new Car("Y", manufacturer);
    private final Car car2 = new Car("RS7", manufacturer2);
    private final Car car3 = new Car("E63 S", manufacturer3);
    private final Driver driverEd
            = new Driver("Ed", "123qwe222113");
    private final Driver driverJohn
            = new Driver("John", "AAAEWWW222JHDSF8899");
    private final Driver driverBob
            = new Driver("BOB", "IEH709898SDF7689SDF231");
    private final ManufacturerService manufacturerService
            = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
    private final CarService carService
            = (CarService) INJECTOR.getInstance(CarService.class);
    private final DriverService driverService
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        manufacturerService.create(manufacturer);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);
        carService.create(car);
        carService.create(car2);
        carService.create(car3);
        driverService.create(driverEd);
        driverService.create(driverJohn);
        driverService.create(driverBob);
        carService.addDriverToCar(driverJohn, car);
        carService.addDriverToCar(driverEd, car);
        carService.addDriverToCar(driverEd, car2);
        carService.addDriverToCar(driverBob, car2);
        carService.addDriverToCar(driverJohn, car3);
        carService.addDriverToCar(driverBob, car3);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
