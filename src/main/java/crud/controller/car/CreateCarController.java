package crud.controller.car;

import crud.lib.Injector;
import crud.model.Car;
import crud.model.Manufacturer;
import crud.service.abstraction.CarService;
import crud.service.abstraction.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("crud");
    private final CarService carService
            = (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService
            = (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String model = req.getParameter("car_model");
        String manufacturerId = req.getParameter("manufacturer_id");
        Manufacturer manufacturer = manufacturerService.get(Long.valueOf(manufacturerId));
        carService.create(new Car(model, manufacturer));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
