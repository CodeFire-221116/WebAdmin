package ua.com.codefire.cms.web.servlet.admin;

import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.implemetation.UserService;
import ua.com.codefire.cms.utils.Utils;

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
        if (Utils.isValid(action)) {

            String sId = req.getParameter("id");
            long id = 0;
            if (Utils.isValid(sId)) {
                id = Long.parseLong(sId);
            }

            if ("new".equals(action)) {
                String confirmation = req.getParameter("submission");
                if (confirmation != null && "SUBMIT".equals(confirmation)) {
                    String username = req.getParameter("username");
                    req.setAttribute("usernameValue", username);
                    if (!req.getParameter("password").equals(req.getParameter("confirm_password"))) {
                        req.setAttribute("errorMessage", "The confirmation password does not match new password!");
                        req.setAttribute("classAdditionForNewPassword", " has-error");
                        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                        return;
                    } else {
                        String newPass = req.getParameter("password");
                        if (Utils.isValid(username)) {
                            if (Utils.isValid(newPass)) {
                                new UserService(req).create(new UserEntity(username, newPass));
                            } else {
                                req.setAttribute("errorMessage", "Password is empty!");
                                req.setAttribute("classAdditionForNewPassword", " has-error");
                                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                                return;
                            }
                        } else {
                            req.setAttribute("errorMessage", "Username is empty!");
                            req.setAttribute("classAdditionForUsername", " has-error");
                            req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                            return;
                        }
                    }
                }
            } else if ("edit".equals(action)) {
                UserEntity user = new UserService(req).read(id);
                String confirmation = req.getParameter("submission");
                req.setAttribute("userName", user.getUsername());
                if (confirmation != null && "SUBMIT".equals(confirmation)) {
                    String newUserName = req.getParameter("username");
                    if(newUserName != null && !newUserName.equals(user.getUsername()))
                        user.setUsername(newUserName);
                    if (!req.getParameter("password").equals(req.getParameter("confirm_password"))) {
                        req.setAttribute("errorMessage", "The confirmation password does not match new password!");
                        req.setAttribute("classAdditionForNewPassword", " has-error");
//                        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
//                        return;
                    } else {
                        String oldPass = req.getParameter("current_password");
                        String newPass = req.getParameter("password");
                        if (oldPass != null && user.checkPassword(oldPass)) {
                            if (Utils.isValid(newPass)) {
                                user.updatePassword(newPass);
                                new UserService(req).update(user);
                            } else {
                                req.setAttribute("errorMessage", "New password is empty!");
                                req.setAttribute("classAdditionForNewPassword", " has-error");
//                                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
//                                return;
                            }
                        } else {
                            req.setAttribute("errorMessage", "Current password is incorrect!");
                            req.setAttribute("classAdditionForCurrentPassword", " has-error");
//                            req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
//                            return;
                        }
                    }
                    if(req.getAttribute("errorMessage") != null) {
                        req.setAttribute("id", user.getId());
                        req.setAttribute("email", user.getEmail());
                        req.setAttribute("userName", user.getUsername());
                        req.setAttribute("userAccessLvl", user.getAccessLvl());
                        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                        return;
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
        if (Utils.isValid(action)) {

            String sId = req.getParameter("id");
            long id = 0;
            if (Utils.isValid(sId)) {
                id = Long.parseLong(sId);
            }
            UserEntity userEntity = new UserService(req).read(id);

            if ("email_validation".equals(action)) {
                new UserService(req).sendValidationEmail(id);
                resp.sendRedirect("/admin/users");
                return;
            } else if ("new".equals(action) || "edit".equals(action)) {
                //ankys changes for editing enabling
                if(userEntity != null) {
                    req.setAttribute("id", userEntity.getId());
                    req.setAttribute("email", userEntity.getEmail());
                    req.setAttribute("userName", userEntity.getUsername());
                    req.setAttribute("userAccessLvl", userEntity.getAccessLvl());
                }

                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                return;
            } else if ("edit".equals(action)) {
                req.setAttribute("userName", userEntity.getUsername());
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/new.jsp").forward(req, resp);
                return;
            } else if ("delete".equals(action)) {
                req.setAttribute("userName", userEntity.getUsername());
                req.getRequestDispatcher("/WEB-INF/jsp/admin/users/confirm_deletion.jsp").forward(req, resp);
                return;
            }
        }
        List<UserEntity> usersList = new UserService(req).getAllEntities();
        req.setAttribute("usersList", usersList);
        req.setAttribute("usersCount", usersList.size());
        req.getRequestDispatcher("/WEB-INF/jsp/admin/users/list.jsp").forward(req, resp);

    }
}
