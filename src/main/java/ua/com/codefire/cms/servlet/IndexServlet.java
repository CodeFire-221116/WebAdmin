package ua.com.codefire.cms.servlet;

import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.implemetation.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by human on 12/1/16.
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
//        UserService userService = new UserService(getServletContext());
//        //System.out.println("userService init: " + userService);
//        UserEntity user;
//
//        while (true) {
//            user = userService.getUserByName("pupkin");
//            if (user != null) {
//                userService.delete(user.getId());
//            } else break;
//        }
//
//        user = userService.getUserByName("pupkin");
//        if (user == null) {
//            userService.create(new UserEntity("pupkin", "12345"));
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }
}
