package crud.controller.manufacturer;

import crud.lib.Injector;
import crud.service.abstraction.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("crud");
    private final ManufacturerService manufacturerService
            = (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("manufacturer_id");
        manufacturerService.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/manufacturers/");
    }
}
