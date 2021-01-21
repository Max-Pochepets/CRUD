package crud.controller.car;

import crud.lib.Injector;
import crud.service.abstraction.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final CarService CAR_SERVICE
            = (CarService) INJECTOR.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("car_id");
        CAR_SERVICE.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/cars/");
    }
}
