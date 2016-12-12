package ua.com.codefire.cms.servlet.admin;

import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.implemetation.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Valery on 08.12.2016.
 */
@WebServlet("/admin/users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("post: " + action);
        if (action != null && !action.isEmpty()) {

            String sId = req.getParameter("id");
            long id = 0;
            if (sId != null && !sId.isEmpty()) {
                id = Long.parseLong(sId);
            }

            if ("new".equals(action)) {
                String confirmation = req.getParameter("submition");
                if (confirmation != null && "SUBMIT".equals(confirmation)) {
                    if (!req.getParameter("password").toString().equals(req.getParameter("confirmpassword").toString())) {
                        req.setAttribute("errorMessage", "The confirmation password does not match new password!");
                        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                        return;
                    } else {
                        String newPass = req.getParameter("password");
                        String username = req.getParameter("username");
                        if (username != null && !username.isEmpty()) {
                            if (newPass != null && !newPass.isEmpty()) {
                                new UserService(req).create(new UserEntity(username, newPass));
                            } else {
                                req.setAttribute("errorMessage", "Password is empty!");
                                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                                return;
                            }
                        } else {
                            req.setAttribute("errorMessage", "Username is empty!");
                            req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                            return;
                        }
                    }
                }
            } else if ("changepassword".equals(action)) {
                String confirmation = req.getParameter("submition");
                if (confirmation != null && "SUBMIT".equals(confirmation)) {
                    if (!req.getParameter("password").toString().equals(req.getParameter("confirmpassword").toString())) {
                        req.setAttribute("errorMessage", "The confirmation password does not match new password!");
                        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/changepassword.jsp").forward(req, resp);
                        return;
                    } else {
                        UserEntity user = new UserService(req).read(id);
                        String oldPass = req.getParameter("oldpassword");
                        String newPass = req.getParameter("password");
                        if (oldPass != null && user.checkPassword(oldPass)) {
                            if (newPass != null && !newPass.isEmpty()) {
                                user.updatePassword(newPass);
                                new UserService(req).update(user);
                            } else {
                                req.setAttribute("errorMessage", "New password is empty!");
                                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/changepassword.jsp").forward(req, resp);
                                return;
                            }
                        } else {
                            req.setAttribute("errorMessage", "Old password is incorrect!");
                            req.getRequestDispatcher("/WEB-INF/jsp/admin/users/changepassword.jsp").forward(req, resp);
                            return;
                        }
                    }
                }
            } else if ("delete".equals(action)) {
                String confirmation = req.getParameter("confirmation");
                if (confirmation != null && "CONFIRM".equals(confirmation)) {
                    new UserService(req).delete(id);
                }
            }

        }
        resp.sendRedirect("/admin/users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("get: " + action);
        if (action != null && !action.isEmpty()) {

            String sId = req.getParameter("id");
            long id = 0;
            if (sId != null && !sId.isEmpty()) {
                id = Long.parseLong(sId);
            }
            UserEntity userEntity = new UserService(req).read(id);

            if ("new".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                return;
            } else if ("changepassword".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/changepassword.jsp").forward(req, resp);
                return;
            } else if ("delete".equals(action)) {
                req.setAttribute("userName", userEntity.getUsername());
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/confirmdeletion.jsp").forward(req, resp);
                return;
            }
        }
        List<UserEntity> usersList = new UserService(req).getAllEntities();
        req.setAttribute("usersList", usersList);
        req.setAttribute("usersCount", usersList.size());
        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/list.jsp").forward(req, resp);

    }
}
