package crud.controller.driver;

import crud.lib.Injector;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriverController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final DriverService DRIVER_SERVICE
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("driver_id");
        DRIVER_SERVICE.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/drivers/");
    }
}