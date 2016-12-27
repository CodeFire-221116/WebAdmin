package ua.com.codefire.cms.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;

import java.util.List;

/**
 * Created by human on 12/27/16.
 */
@RestController
@RequestMapping(path = "/rest/articles")
public class ArticlesRestController {

    @Autowired
    private IArticleService service;

    @ResponseBody
    @RequestMapping(path = "/")
    public List<ArticleEntity> getArticlesList() {
        return service.getAllEntities();
    }

    @ResponseBody
    @RequestMapping(path = "/", method = {RequestMethod.POST})
    public ArticleEntity addArticle(ArticleEntity article) {
        Long id = service.create(article);

        article.setId(id);

        return article;
    }

    @ResponseBody
    @RequestMapping(path = "/{id}", method = {RequestMethod.GET})
    public ArticleEntity getArticle(@PathVariable Long id) {
        return service.read(id);
    }

    @ResponseBody
    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT})
    public ArticleEntity editArticle(@PathVariable Long id, ArticleEntity article) {
        article.setId(id);
        service.update(article);

        return article;
    }

}
