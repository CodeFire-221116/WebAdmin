package ua.com.codefire.cms.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.db.service.implemetation.UserService;
import ua.com.codefire.cms.utils.Utils;
import ua.com.codefire.cms.web.components.MailComponent;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

/**
 * Created by User on 24.12.2016.
 */
@Controller
public class IndexController {

    @Autowired
    private MailComponent mailComponent;

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(path = "/mail", method = {RequestMethod.POST})
    public String postMail(@RequestParam("to") InternetAddress[] to) throws MessagingException, UnsupportedEncodingException {
        System.out.println(to);

        String message = "<h1>Welcome to system!</h1>";
        mailComponent.sendMail("Invite", message, to);

        return "index";
    }


    protected String getIndexPage(@RequestParam("umv") String validationCode) {
        if (Utils.isValid(validationCode)) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            IUserService userService = new UserService(attr.getRequest());
            if (userService.validateEmail(validationCode)) {
                System.out.println("Email validation with code: " + validationCode + " is OK");
            } else {
                System.out.println("Error email validation with code: " + validationCode);
            }
            return "redirect:/index";
        }
        return "index";
    }
}
