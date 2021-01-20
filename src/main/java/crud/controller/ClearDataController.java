package crud.controller;

import crud.util.ConnectionUtil;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearDataController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ConnectionUtil.clearTable();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
