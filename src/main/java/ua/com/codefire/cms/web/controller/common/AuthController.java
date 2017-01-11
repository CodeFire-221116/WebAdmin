package ua.com.codefire.cms.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.db.service.implemetation.UserService;
import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.http.HttpSession;

/**
 * Created by User on 24.12.2016.
 */
@Controller
public class AuthController {
    @RequestMapping(path = "/auth")
    public String getAuth() {
        return "auth";
    }

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public String postAuth(@RequestParam String username, @RequestParam String password, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        IUserService service = new UserService(attr.getRequest());

        if (service.ifUserRegistered(username, password)) {
            UserEntity currUser = service.getUserByName(username);
            session.setAttribute(AttributeNames.SESSION_AUTHENTICATED, true);
            session.setAttribute(AttributeNames.SESSION_USER, currUser);
            //Left userName, because it is used too many times in too many places, need time to change. Need to change to user
            session.setAttribute(AttributeNames.SESSION_USERNAME, username);
            if (currUser.getAccessLvl() == UserEntity.AccessLevel.User) {
                return "redirect:/index";
            } else {
                return "redirect:/admin/index";
            }
        } else {
            session.setAttribute("flash_message", "Username or password is incorrect.");
            return "/auth";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String getLogout(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        IUserService service = new UserService(attr.getRequest());

        session.setAttribute(AttributeNames.SESSION_AUTHENTICATED, false);
        session.removeAttribute(AttributeNames.SESSION_USER);
        //Left userName, because it is used too many times in too many places, need time to change. Need to change to user
        session.removeAttribute(AttributeNames.SESSION_USERNAME);
        return "redirect:/index";
    }

    @RequestMapping(path = "/register")
    public String getUsers() {
        return "userreg/userreg";
    }
}
