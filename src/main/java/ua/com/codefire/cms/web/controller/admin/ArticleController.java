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
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;
import ua.com.codefire.cms.db.service.abstraction.IUserService;
import ua.com.codefire.cms.model.AttributeNames;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 25.12.2016.
 */
@RequestMapping(path = "/admin/articles")
@Controller
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getArticlesList(Model model) {
        List<ArticleEntity> articles = articleService.getAllEntities();

        model.addAttribute("articlesList", articles);
        model.addAttribute("count", articles.size());

        return "admin/article/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateArticlePage() {
        return "admin/article/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateArticle(@Validated @ModelAttribute ArticleEntity articleEntity, BindingResult result) {
        if(result.hasErrors())
            throw new DataIntegrityViolationException("Wrong Data entered");
        articleEntity.setDate(new Timestamp(new Date().getTime()));
        articleService.create(articleEntity);
        return "redirect:/admin/articles/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getArticleEditPage(@RequestParam Long id, Model model) {
        ArticleEntity articleToEdit = articleService.read(id);

        model.addAttribute("IDtoedit", id);
        model.addAttribute("Authortoedit", articleToEdit.getAuthors());
        model.addAttribute("Contenttoedit", articleToEdit.getContent());
        model.addAttribute("Titletoedit", articleToEdit.getTitle());
        model.addAttribute("Datetoedit", articleToEdit.getDate());

        return "admin/article/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateArticle(@Validated @ModelAttribute ArticleEntity articleEntity, BindingResult result) {
        if(result.hasErrors())
            throw new DataIntegrityViolationException("Wrong Data entered");
        articleEntity.setDate(new Timestamp(new Date().getTime()));
        articleService.update(articleEntity);
        return "redirect:/admin/articles/";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteArticle(@RequestParam Long id) {
        articleService.delete(id);
        return "redirect:/admin/articles/";
    }

    @ResponseBody
    @RequestMapping(value = "/favourite", method = RequestMethod.PUT)
    public Boolean changeFavourite(@RequestParam Long id, HttpServletRequest request)
    {
        ArticleEntity articleEntity = articleService.read(id);
        UserEntity currUser = (UserEntity)request.getSession().getAttribute(AttributeNames.SESSION_USER);
        currUser.setArticles(new ArrayList<ArticleEntity>(){{
            add(articleEntity);
        }});
        return userService.update(currUser);
    }

    @ResponseStatus(value= HttpStatus.CONFLICT,
            reason="Wrong data entered")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // Nothing to do
    }
}
