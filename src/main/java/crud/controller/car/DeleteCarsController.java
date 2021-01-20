package crud.controller.car;

import crud.lib.Injector;
import crud.service.abstraction.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarsController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final CarService carService
            = (CarService) INJECTOR.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        carService.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/cars/all");
    }
}
