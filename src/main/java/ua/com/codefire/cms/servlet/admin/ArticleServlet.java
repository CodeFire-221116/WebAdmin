package ua.com.codefire.cms.servlet.admin;

import ua.com.codefire.cms.db.entity.Article;
import ua.com.codefire.cms.db.service.implementation.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by mkoval on 10.12.2016.
 */
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && "new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/admin/article/edit.jsp").forward(req, resp);
        } else {
            Long id = null;
            if (req.getParameter("id") != null) {
                id = Long.parseLong(req.getParameter("id"));
            }

            List<Article> articles = new ArticleService(req).getAllEntities();

            if (!articles.isEmpty()) {
                req.setAttribute("articlesList", articles);
                req.setAttribute("count", articles.size());

                for (Article article : articles) {
                    if (id != null && id.equals(article.getId())) {
                        req.setAttribute("IDtoedit", id);
                        req.setAttribute("Aythortoedit", article.getArticleauthor());
                        req.setAttribute("Contenttoedit", article.getArticlecontent());
                        req.setAttribute("Titletoedit", article.getArticletitle());
                        req.setAttribute("Timestamptoedit", article.getArticletimestamp());

                        req.getRequestDispatcher("/WEB-INF/jsp/admin/article/edit.jsp").forward(req, resp);
                        return;
                    }
                }
            }

            req.getRequestDispatcher("/WEB-INF/jsp/admin/article/list.jsp").forward(req, resp);
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        //String articleTimestamp = req.getParameter("articleTimestamp");
        //new Date(articleTimestamp);

        if (action != null && "new".equals(action)) {
            ArticleService newArticle = new ArticleService(req);
            newArticle.update(new Article(
                    req.getParameter("articleTitle"),
                    req.getParameter("articleAuthor"),
                    req.getParameter("articleContent"),
                    new Date ()));




        } else {
            if (action != null && "delete".equals(action)) {


            } else {
                Long id = null;
                if (req.getParameter("id") != null) {
                    id = Long.parseLong(req.getParameter("id"));
                }

                if (id != null) {
                    ArticleService newArticle = new ArticleService(req);
                    newArticle.update(new Article(
                            id,
                            req.getParameter("articleTitle"),
                            req.getParameter("articleAuthor"),
                            req.getParameter("articleContent"),
                            new Date()));
                }
            }
        }
        resp.sendRedirect("/admin/article");
    }
}
