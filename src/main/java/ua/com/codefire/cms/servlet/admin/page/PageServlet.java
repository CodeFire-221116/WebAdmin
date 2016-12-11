package ua.com.codefire.cms.servlet.admin.page;

import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.service.abstraction.IPageService;
import ua.com.codefire.cms.db.service.implemetation.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kris on 08.12.16
 */

@WebServlet("/admin/pages")
public class PageServlet extends HttpServlet {

    private static final String DEFAULT_TITLE = "Title";
    private static final String DEFAULT_CONTENT = "Content";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IPageService service = new PageService(req);

        if (service.getAmountOfPAges() <= 0) {
            createDefaultData(service);
        }

        List<PageEntity> pages = service.getAllEntities();

        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if (action != null && "new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
        } else if (id != null) {

            req.setAttribute("id", id);
            req.setAttribute("title", id);
            req.setAttribute("id", id);

            req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
        } else {

            req.setAttribute("page_count", pages.size());
            req.setAttribute("pages", pages);

            req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void createDefaultData(IPageService service) {
        PageEntity page;

        for (int i = 0; i < 15; i++) {
            page = new PageEntity();
            page.setTitle(DEFAULT_TITLE + " " + i);
            page.setContent(DEFAULT_CONTENT + " " + i);

            service.create(page);
        }
    }
}
