package ua.com.codefire.cms.web.controller.admin;

import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.abstraction.IUserService;

import java.util.List;

@RequestMapping(path = "/admin/users")
@Controller
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getUsersList(Model model) {
        List<UserEntity> users = userService.getAllEntities();
        model.addAttribute("usersList", users);
        model.addAttribute("usersCount", users.size());

        return "/admin/users/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateUserPage() {
        return "/admin/users/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateUser(@RequestParam(name = "username") String userName,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "confirm_password") String passwordConfirmation,
                                 @RequestParam(name = "submission") String confirmation, Model model) {

        if (confirmation != null && "SUBMIT".equals(confirmation)) {
            if (userName.isEmpty()) {
                model.addAttribute("errorMessage", "Username is empty!");
                model.addAttribute("classAdditionForUsername", " has-error");
            } else if (password.isEmpty()) {
                model.addAttribute("errorMessage", "Password is empty!");
                model.addAttribute("classAdditionForNewPassword", " has-error");
                model.addAttribute("userName", userName);
            } else if (!password.equals(passwordConfirmation)) {
                model.addAttribute("errorMessage", "The confirmation password does not match new password!");
                model.addAttribute("classAdditionForNewPassword", " has-error");
                model.addAttribute("userName", userName);
            } else {
                userService.create(new UserEntity(userName, password));
                return "redirect:/admin/users/";
            }
        } else {
            return "redirect:/admin/users/";
        }

        return "/admin/users/new";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getUpdateUser(@RequestParam Long id, Model model) {
        UserEntity user = userService.read(id);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("id", user.getId());
        return "/admin/users/new";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String postUpdateUser(@RequestParam Long id, @RequestParam String userName,
//                                 @RequestParam String email, @RequestParam UserEntity.AccessLevel userAccessLvl) {
    public String postUpdateUser(@RequestParam Long id, @RequestParam(name = "username") String userName,
                                 @RequestParam(name = "submission") String confirmation) {

        if (confirmation != null && "SUBMIT".equals(confirmation)) {
            UserEntity user = userService.read(id);
            user.setUsername(userName);
//            user.setEmail(email);
//            user.setAccessLvl(userAccessLvl);
            userService.update(user);
        }
        return "redirect:/admin/users/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDeleteUser(@RequestParam Long id, Model model) {
        UserEntity user = userService.read(id);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("id", user.getId());
        return "/admin/users/confirm_deletion";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String postDeleteUser(@RequestParam String confirmation, @RequestParam Long id) {
        if (confirmation != null && "CONFIRM".equals(confirmation)) {
            userService.delete(id);
        }
        return "redirect:/admin/users/";
    }


}
