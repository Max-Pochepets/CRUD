package crud.controller;

import crud.controller.security.AuthenticationService;
import crud.lib.Injector;
import crud.lib.exception.AuthenticationException;
import crud.model.Driver;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector INJECTOR = Injector.getInstance("crud");
    private final AuthenticationService authenticationService
            = (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            Driver driver = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute(DRIVER_ID, driver.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("error_message", "Incorrect login or password.");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
