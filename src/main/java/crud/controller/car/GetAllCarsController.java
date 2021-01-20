package crud.controller.car;

import crud.lib.Injector;
import crud.model.Car;
import crud.service.abstraction.CarService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllCarsController extends HttpServlet {
    private static final Injector INJECTOR
            = Injector.getInstance("crud");
    private static final CarService CAR_SERVICE
            = (CarService) INJECTOR.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Car> cars = CAR_SERVICE.getAll();
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
