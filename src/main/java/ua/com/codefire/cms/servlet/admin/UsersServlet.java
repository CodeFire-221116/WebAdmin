package ua.com.codefire.cms.servlet.admin;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Valery on 08.12.2016.
 */
@WebServlet("/admin/users")
public class UsersServlet extends HttpServlet {
    private EntityManagerFactory factory;

    @Override
    public void init() throws ServletException {
        if (factory == null) {
            factory = (EntityManagerFactory) getServletContext().getAttribute("factory");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/users/list.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/users/list.jsp").forward(req, resp);
    }
}
