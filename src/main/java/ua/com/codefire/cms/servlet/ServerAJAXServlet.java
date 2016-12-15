package ua.com.codefire.cms.servlet;

import ua.com.codefire.cms.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by human on 12/15/16.
 */
@WebServlet("/ajax/server")
public class ServerAJAXServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String message = req.getParameter("message");

        if (Utils.isValid(message)) {
            System.out.println(req.getParameter("message"));
        }

        if (Utils.isValid(action)) {
            switch(action.toLowerCase()) {
                case "get_time":
                    resp.getWriter()
                            .printf(Locale.getDefault(), "%s", LocalDateTime.now());
                    break;
            }
        }

    }
}
