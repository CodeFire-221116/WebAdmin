package ua.com.codefire.cms.servlet.admin;

import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.implemetation.ArticleService;
import ua.com.codefire.cms.db.service.implemetation.UserRegServ;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 20.12.2016.
 */
public class UserRegServlet extends  HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/userreg/userreg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("register") != null) {
            registerUser(request);
        } else {
            loginUser(request);
        }
    }

    private void registerUser(HttpServletRequest req) {

        String name = req.getParameter("username");
        String pass = req.getParameter("user_pass");
        String email = req.getParameter("user_email");

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(name);
        userEntity.setPassword(pass);
        userEntity.setEmail(email);

        UserRegServ userRegServ = new UserRegServ();

        userRegServ.register(userEntity);


    }

    private void loginUser(HttpServletRequest req){}
}

