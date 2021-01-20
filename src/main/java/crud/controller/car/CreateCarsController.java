package crud.controller.car;

import crud.lib.Injector;
import crud.model.Car;
import crud.model.Manufacturer;
import crud.service.abstraction.CarService;
import crud.service.abstraction.ManufacturerService;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarsController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final CarService CAR_SERVICE
            = (CarService) INJECTOR.getInstance(CarService.class);
    private static final ManufacturerService MANUFACTURER_SERVICE
            = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String model = req.getParameter("model");
            String manufacturerId = req.getParameter("manufacturersId");
            Manufacturer manufacturer = MANUFACTURER_SERVICE.get(Long.valueOf(manufacturerId));
            CAR_SERVICE.create(new Car(model, manufacturer));
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (NoSuchElementException e) {
            req.setAttribute("message", "Invalid data. No such manufacturer present.");
            req.setAttribute("carModel", req.getParameter("model"));
            req.getRequestDispatcher("/WEB-INF/views/cars/create.jsp").forward(req, resp);
        }
    }
}
