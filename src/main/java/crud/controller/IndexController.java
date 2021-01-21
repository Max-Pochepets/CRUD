package crud.controller;

import crud.lib.Injector;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final DriverService driverService
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }
}
