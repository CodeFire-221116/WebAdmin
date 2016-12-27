package ua.com.codefire.cms.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 25.12.2016.
 */
@RequestMapping(path = "/admin/article")
@Controller
public class ArticleController {
    private IArticleService articleService;

    @Autowired
    public ArticleController(IArticleService articleService)
    {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getArticlesList(Model model) {
        List<ArticleEntity> articles = articleService.getAllEntities();

        model.addAttribute("articlesList", articles);
        model.addAttribute("count", articles.size());

        return "admin/article/list";
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateArticlePage() {
        return "admin/article/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateArticle(@RequestParam String articleTitle, @RequestParam String articleAuthor,
                                    @RequestParam String articleContent) {
        articleService.create(new ArticleEntity(
                articleTitle,
                articleAuthor,
                new Timestamp(new Date().getTime()),
                articleContent));
        return "redirect:/admin/article/list";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteArticle(@RequestParam Long id) {
        articleService.delete(id);
        return "redirect:/admin/article/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateArticle(@RequestParam Long id, @RequestParam String articleTitle, @RequestParam String articleAuthor,
                                    @RequestParam String articleContent) {
        ArticleEntity objToUpdate = articleService.read(id);
        objToUpdate.setTitle(articleTitle);
        objToUpdate.setAuthors(articleAuthor);
        objToUpdate.setDate(new Timestamp(new Date().getTime()));
        objToUpdate.setContent(articleContent);
        articleService.update(objToUpdate);
        return "redirect:/admin/article/list";
    }
}
