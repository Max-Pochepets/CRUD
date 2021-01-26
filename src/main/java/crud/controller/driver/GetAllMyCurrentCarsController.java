package crud.controller.driver;

import crud.lib.Injector;
import crud.model.Car;
import crud.service.abstraction.CarService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllMyCurrentCarsController extends HttpServlet {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector = Injector.getInstance("crud");
    private final CarService carService
            = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        List<Car> allCarsByDriver = carService.getAllByDriver(driverId);
        req.setAttribute("cars", allCarsByDriver);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
