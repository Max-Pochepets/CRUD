package crud.controller.car;

import crud.lib.Injector;
import crud.model.Car;
import crud.model.Driver;
import crud.service.abstraction.CarService;
import crud.service.abstraction.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriverFromCar extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private static final CarService carService
            = (CarService) INJECTOR.getInstance(CarService.class);
    private static final DriverService driverService
            = (DriverService) INJECTOR.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String carID = req.getParameter("carID");
        String driverID = req.getParameter("driverID");
        Car car = carService.get(Long.valueOf(carID));
        Driver driver = driverService.get(Long.valueOf(driverID));
        carService.removeDriverFromCar(driver, car);
        resp.sendRedirect(req.getContextPath() + "/cars/all");
    }
}
