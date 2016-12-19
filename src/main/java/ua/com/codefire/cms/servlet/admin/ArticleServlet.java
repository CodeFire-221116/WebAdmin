package ua.com.codefire.cms.servlet.admin;


import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.service.implemetation.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;


/**
 * Created by mkoval on 10.12.2016.
 */
@WebServlet("/admin/articles")
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

            List<ArticleEntity> articles = new ArticleService(req).getAllEntities();

            if (!articles.isEmpty()) {
                req.setAttribute("articlesList", articles);
                req.setAttribute("count", articles.size());

                for (ArticleEntity article : articles) {
                    if (id != null && id.equals(article.getId())) {
                        req.setAttribute("IDtoedit", id);
                        req.setAttribute("Aythortoedit", article.getAuthors());
                        req.setAttribute("Contenttoedit", article.getContent());
                        req.setAttribute("Titletoedit", article.getTitle());
                        req.setAttribute("Datetoedit", article.getDate());

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
            newArticle.update(new ArticleEntity(
                    req.getParameter("Title"),
                    req.getParameter("Author"),
                    new Timestamp(new Date().getTime()),
                    req.getParameter("Content")));




        } else {
            if (action != null && "delete".equals(action)) {


            } else {
                Long id = null;
                if (req.getParameter("id") != null) {
                    id = Long.parseLong(req.getParameter("id"));
                }

                if (id != null) {
                    ArticleService newArticle = new ArticleService(req);
                    ArticleEntity objToUpdate = newArticle.read(id);
                    objToUpdate.setTitle(req.getParameter("Title"));
                    objToUpdate.setAuthors(req.getParameter("Author"));
                    objToUpdate.setDate(new Timestamp(new Date().getTime()));
                    objToUpdate.setContent(req.getParameter("Content"));
                    newArticle.update(objToUpdate);
                }
            }
        }
        resp.sendRedirect("/admin/article");
    }
}
