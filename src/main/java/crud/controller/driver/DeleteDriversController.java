package crud.controller.driver;

import crud.lib.Injector;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriversController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final DriverService driverService
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        driverService.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/drivers/all");
    }
}