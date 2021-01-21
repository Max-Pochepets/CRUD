package crud.controller.driver;

import crud.lib.Injector;
import crud.model.Driver;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDriverController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final DriverService driverService
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("driver_name");
        String license = req.getParameter("driver_license");
        String driverLogin = req.getParameter("driver_login");
        String driverPassword = req.getParameter("driver_password");
        driverService.create(new Driver(name, license, driverLogin, driverPassword));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
