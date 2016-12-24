package ua.com.codefire.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by User on 23.12.2016.
 */
@Controller
@RequestMapping(path = "/users")
public class UserController {
    @ResponseBody
    @RequestMapping(path = "/list")
    public String getUsers() {
        return "Hello";
    }
}
