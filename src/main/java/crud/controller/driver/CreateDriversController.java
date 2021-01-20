package crud.controller.driver;

import crud.lib.Injector;
import crud.model.Driver;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDriversController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final DriverService driverService
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String license = req.getParameter("license");
        driverService.create(new Driver(name, license));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
