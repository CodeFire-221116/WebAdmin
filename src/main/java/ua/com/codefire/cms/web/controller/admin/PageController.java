package ua.com.codefire.cms.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;
import ua.com.codefire.cms.db.service.abstraction.IPageService;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.model.AttributeNames;
import ua.com.codefire.cms.model.Fields;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 25.12.2016.
 */
@RequestMapping(path = "/admin/pages")
@Controller
public class PageController {
    @Autowired
    private IPageService pageService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getArticlesList(Model model, HttpServletRequest request) {
        List<PageEntity> pages = pageService.getAllEntities();

        model.addAttribute(Fields.PAGE_COUNT, pages.size());
        model.addAttribute(Fields.PAGES, pages);

        return "admin/pages/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateArticlePage() {
        return "admin/pages/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateArticle(@Validated @ModelAttribute PageEntity pageEntity, BindingResult result) {
        if(result.hasErrors())
            throw new DataIntegrityViolationException("Wrong Data entered");
        pageService.create(pageEntity);
        return "redirect:/admin/pages/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getArticleEditPage(@RequestParam Long id, Model model) {
        PageEntity pageToEdit = pageService.read(id);

        model.addAttribute(Fields.ID, pageToEdit.getId());
        model.addAttribute(Fields.TITLE, pageToEdit.getTitle());
        model.addAttribute(Fields.CONTENT, pageToEdit.getContent());

        return "admin/pages/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateArticle(@Validated @ModelAttribute PageEntity pageEntity, BindingResult result) {
        if(result.hasErrors())
            throw new DataIntegrityViolationException("Wrong Data entered");
        pageService.update(pageEntity);

        return "redirect:/admin/pages/";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteArticle(@RequestParam Long id) {
        pageService.delete(id);
        return "redirect:/admin/pages/";
    }

    @ResponseStatus(value= HttpStatus.CONFLICT,
            reason="Wrong data entered")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // Nothing to do
    }
}
