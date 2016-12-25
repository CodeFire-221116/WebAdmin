package ua.com.codefire.cms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by User on 25.12.2016.
 */
@RequestMapping(path = "/admin")
@Controller
public class IndexController {
    @RequestMapping(path="/index")
    public String getAdminIndex(){
        return "admin/index";
    }
}
