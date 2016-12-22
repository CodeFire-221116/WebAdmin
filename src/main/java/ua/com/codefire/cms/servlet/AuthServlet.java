package ua.com.codefire.cms.servlet;

import ua.com.codefire.cms.db.entity.*;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;
import ua.com.codefire.cms.db.service.abstraction.IBookService;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.db.service.implemetation.*;
import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by human on 12/1/16.
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        IUserService service = new UserService(req);

        HttpSession session = req.getSession();
        // TODO: Validate user by database data.
        if (service.ifUserRegistered(username, password)) {
            UserEntity currUser = service.getUserByName(username);
            session.setAttribute(AttributeNames.SESSION_AUTHENTICATED, true);
            session.setAttribute(AttributeNames.SESSION_USER, currUser);
            //Left userName, because it is used too many times in too many places, need time to change
            session.setAttribute(AttributeNames.SESSION_USERNAME, username);
        } else {
            session.setAttribute("flash_message", "Username or password is incorrect.");
        }

        resp.sendRedirect("/admin/");
    }
}
