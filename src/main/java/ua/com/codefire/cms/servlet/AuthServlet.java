package ua.com.codefire.cms.servlet;

import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

            HttpSession session = req.getSession();
        // TODO: Validate user by database data.
        if (username != null && username.equals("pupkin") && password != null && password.equals("12345")) {
            session.setAttribute(AttributeNames.SESSION_AUTHENTICATED, true);
            session.setAttribute(AttributeNames.SESSION_USERNAME, username);
        } else {
            session.setAttribute("flash_message", "Username or password is incorrect.");
        }

        resp.sendRedirect("/");
    }
}
