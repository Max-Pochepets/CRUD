package crud.controller.driver;

import crud.lib.Injector;
import crud.model.Driver;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllDriversController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final DriverService DRIVER_SERVICE
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Driver> drivers = DRIVER_SERVICE.getAll();
        req.setAttribute("drivers", drivers);
        req.getRequestDispatcher("/WEB-INF/views/drivers/all.jsp").forward(req, resp);
    }
}
