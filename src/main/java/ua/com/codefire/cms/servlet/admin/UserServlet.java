package ua.com.codefire.cms.servlet.admin;

import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.implemetation.UserService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Valery on 08.12.2016.
 */
@WebServlet("/admin/users")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        if (userService == null) {
            userService = new UserService(getServletContext());
            System.out.println("userService init: " + userService);
//            userService = new UserService((ServletRequest) this);
            getServletContext().setAttribute("userService", userService);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("post: " + action);
        if (action != null && !action.isEmpty()) {

            String sId = req.getParameter("id");
            long id;
            if (sId != null && !sId.isEmpty()) {
                id = Long.parseLong(sId);
            }

            if ("new".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                return;
            } else if ("changepassword".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/changepassword.jsp").forward(req, resp);
                return;
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/list.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("get: " + action);
        if (action != null && !action.isEmpty()) {

            String sId = req.getParameter("id");
            long id;
            if (sId != null && !sId.isEmpty()) {
                id = Long.parseLong(sId);
            }

            if ("new".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                return;
            } else if ("changepassword".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/changepassword.jsp").forward(req, resp);
                return;
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/list.jsp").forward(req, resp);

    }
}
