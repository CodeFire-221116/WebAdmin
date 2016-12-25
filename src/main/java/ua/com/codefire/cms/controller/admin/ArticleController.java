package ua.com.codefire.cms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;
import ua.com.codefire.cms.db.service.implemetation.ArticleService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 25.12.2016.
 */
@RequestMapping(path = "/admin/article")
@Controller
public class ArticleController {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getArticlesList(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        List<ArticleEntity> articles = new ArticleService(attr.getRequest()).getAllEntities();

        model.addAttribute("articlesList", articles);
        model.addAttribute("count", articles.size());

        return "admin/article/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getArticleEditPage(@RequestParam Long id, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        IArticleService service = new ArticleService(attr.getRequest());

        ArticleEntity articleToEdit = service.read(id);

        model.addAttribute("IDtoedit", id);
        model.addAttribute("Authortoedit", articleToEdit.getAuthors());
        model.addAttribute("Contenttoedit", articleToEdit.getContent());
        model.addAttribute("Titletoedit", articleToEdit.getTitle());
        model.addAttribute("Datetoedit", articleToEdit.getDate());

        return "admin/article/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateArticlePage() {
        return "admin/article/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateArticle(@RequestParam String articleTitle, @RequestParam String articleAuthor,
                                    @RequestParam String articleContent, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ArticleService newArticle = new ArticleService(attr.getRequest());
        newArticle.create(new ArticleEntity(
                articleTitle,
                articleAuthor,
                new Timestamp(new Date().getTime()),
                articleContent));
        return "redirect:/admin/article/list";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteArticle(@RequestParam Long id) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ArticleService articleService = new ArticleService(attr.getRequest());

        articleService.delete(id);
        return "redirect:/admin/article/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateArticle(@RequestParam Long id, @RequestParam String articleTitle, @RequestParam String articleAuthor,
                                    @RequestParam String articleContent, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ArticleService newArticle = new ArticleService(attr.getRequest());

        ArticleEntity objToUpdate = newArticle.read(id);
        objToUpdate.setTitle(articleTitle);
        objToUpdate.setAuthors(articleAuthor);
        objToUpdate.setDate(new Timestamp(new Date().getTime()));
        objToUpdate.setContent(articleContent);
        newArticle.update(objToUpdate);
        return "redirect:/admin/article/list";
    }
}
