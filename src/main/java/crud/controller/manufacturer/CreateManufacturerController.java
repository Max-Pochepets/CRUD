package crud.controller.manufacturer;

import crud.lib.Injector;
import crud.model.Manufacturer;
import crud.service.abstraction.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateManufacturerController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final ManufacturerService manufacturerService
            = (ManufacturerService) INJECTOR.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        manufacturerService.create(new Manufacturer(name, country));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
