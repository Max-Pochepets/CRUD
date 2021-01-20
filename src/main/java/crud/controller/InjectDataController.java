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
    private static final Manufacturer MANUFACTURER
            = new Manufacturer("Tesla", "USA");
    private static final Manufacturer MANUFACTURER_2
            = new Manufacturer("Audi", "Germany");
    private static final Manufacturer MANUFACTURER_3
            = new Manufacturer("Mercedes", "Germany");
    private static final Car CAR = new Car("Y", MANUFACTURER);
    private static final Car CAR_2 = new Car("RS7", MANUFACTURER_2);
    private static final Car CAR_3 = new Car("E63 S", MANUFACTURER_3);
    private static final Driver DRIVER_ED
            = new Driver("Ed", "123qwe222113");
    private static final Driver DRIVER_JOHN
            = new Driver("John", "AAAEWWW222JHDSF8899");
    private static final Driver DRIVER_BOB
            = new Driver("BOB", "IEH709898SDF7689SDF231");
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final ManufacturerService MANUFACTURER_SERVICE
            = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);
    private static final CarService CAR_SERVICE
            = (CarService) INJECTOR.getInstance(CarService.class);
    private static final DriverService DRIVER_SERVICE
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        MANUFACTURER_SERVICE.create(MANUFACTURER);
        MANUFACTURER_SERVICE.create(MANUFACTURER_2);
        MANUFACTURER_SERVICE.create(MANUFACTURER_3);
        CAR_SERVICE.create(CAR);
        CAR_SERVICE.create(CAR_2);
        CAR_SERVICE.create(CAR_3);
        DRIVER_SERVICE.create(DRIVER_ED);
        DRIVER_SERVICE.create(DRIVER_JOHN);
        DRIVER_SERVICE.create(DRIVER_BOB);
        CAR_SERVICE.addDriverToCar(DRIVER_JOHN, CAR);
        CAR_SERVICE.addDriverToCar(DRIVER_ED, CAR);
        CAR_SERVICE.addDriverToCar(DRIVER_ED, CAR_2);
        CAR_SERVICE.addDriverToCar(DRIVER_BOB, CAR_2);
        CAR_SERVICE.addDriverToCar(DRIVER_JOHN, CAR_3);
        CAR_SERVICE.addDriverToCar(DRIVER_BOB, CAR_3);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
