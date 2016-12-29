package ua.com.codefire.cms.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public String postCreateUser(@Validated @ModelAttribute UserEntity userEntity,
                                 @RequestParam(name = "confirm_password") String passwordConfirmation,
                                 @RequestParam(name = "submission") String confirmation, Model model,
                                 BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
        }

//        if (confirmation != null && "SUBMIT".equals(confirmation)) {
//            if (userName.isEmpty()) {
//                model.addAttribute("errorMessage", "Username is empty!");
//                model.addAttribute("classAdditionForUsername", " has-error");
//            } else if (password.isEmpty()) {
//                model.addAttribute("errorMessage", "Password is empty!");
//                model.addAttribute("classAdditionForNewPassword", " has-error");
//                model.addAttribute("userName", userName);
//            } else if (!password.equals(passwordConfirmation)) {
//                model.addAttribute("errorMessage", "The confirmation password does not match new password!");
//                model.addAttribute("classAdditionForNewPassword", " has-error");
//                model.addAttribute("userName", userName);
//            } else {
//                UserEntity user = new UserEntity(userName, password);
//                user.setAccessLvl(UserEntity.AccessLevel.User);
//                userService.create(user);
//                return "redirect:/admin/users/";
//            }
//        } else {
//            return "redirect:/admin/users/";
//        }

        return "/admin/users/new";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getUpdateUser(@RequestParam Long id, Model model, HttpSession session) {

        UserEntity currentUser = (UserEntity) session.getAttribute(AttributeNames.SESSION_USER);

        UserEntity user = userService.read(id);
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("email", user.getEmail());
        UserEntity.AccessLevel userAccessLevel = user.getAccessLvl();
        if (userAccessLevel == null) {
            userAccessLevel = UserEntity.AccessLevel.User;
        }
        model.addAttribute("userAccessLevel", userAccessLevel);
        StringBuilder userAccessLevels = new StringBuilder();
        for (UserEntity.AccessLevel accessLevel: UserEntity.AccessLevel.values())
        {
            if (currentUser.canChangeAccessLvl(accessLevel) || userAccessLevel == accessLevel) {
                userAccessLevels.append("<option");
                if (accessLevel.equals(userAccessLevel)) userAccessLevels.append(" selected");
                userAccessLevels.append(">").append(accessLevel).append("</option>");
            }
        }
        model.addAttribute("id", user.getId());
        model.addAttribute("userAccessLevels", userAccessLevels.toString());

        return "/admin/users/new";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateUser(@RequestParam Long id, @RequestParam(name = "username") String userName,
                                 @RequestParam(name = "submission") String confirmation,
                                 @RequestParam String email, @RequestParam UserEntity.AccessLevel userAccessLevel) {

        if (confirmation != null && "SUBMIT".equals(confirmation)) {
            UserEntity user = userService.read(id);
            user.setUsername(userName);
            if (!email.isEmpty() && !email.equals(user.getEmail())) {
                user.setEmail(email);
                user.setEmailKey(new Date().getTime());
            }
            user.setAccessLvl(userAccessLevel);
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

    @RequestMapping(value = "/email_validation", method = RequestMethod.GET)
    public String getSendEmailValidation(@RequestParam Long id) {
        userService.sendValidationEmail(id);
        return "redirect:/admin/users/";
    }


}
