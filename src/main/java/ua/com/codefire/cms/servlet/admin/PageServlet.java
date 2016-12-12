package ua.com.codefire.cms.servlet.admin;

import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.service.abstraction.IPageService;
import ua.com.codefire.cms.db.service.implemetation.PageService;
import ua.com.codefire.cms.model.Constants;
import ua.com.codefire.cms.utils.Utils;

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

    private PageService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service = new PageService(req);

        if (service.getAmountOfPAges() <= 0) {
            prepareDefaultData(service);
        }


        String action = req.getParameter(Constants.ACTION);
        String id = req.getParameter(Constants.ID);

        if (action != null && Constants.ACTION_NEW.equals(action)) {
            createPage(req, resp);
        } else if (id != null) {
            editPage(req, resp, Long.parseLong(id));
        } else {
            showAllPages(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service = new PageService(req);

        String idStr = req.getParameter(Constants.ID);
        String title = req.getParameter(Constants.TITLE);
        String content = req.getParameter(Constants.CONTENT);

        if (req.getParameter(Constants.BTN_OK) != null) {

            if (Utils.isValid(title) && Utils.isValid(content)) {

                if (idStr != null) {
                    service.update(getPage(Long.parseLong(idStr), title, content));
                } else {
                    service.create(getPage(null, title, content));
                }

                resp.sendRedirect("/admin/pages");
            } else {
                req.setAttribute(Constants.ERROR_MESSAGE, "Fields title and content must be filled");
                req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
            }
        } else if (req.getParameter(Constants.BTN_DELETE) != null) {
            if (idStr != null) {
                service.delete(Long.parseLong(idStr));
                resp.sendRedirect("/admin/pages");
            }
        }

    }

    private void createPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
    }

    private void editPage(HttpServletRequest req, HttpServletResponse resp, Long id)
            throws ServletException, IOException {

        PageEntity page = service.read(id);

        req.setAttribute(Constants.ID, page.getId());
        req.setAttribute(Constants.TITLE, page.getTitle());
        req.setAttribute(Constants.CONTENT, page.getContent());

        req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/edit.jsp").forward(req, resp);
    }

    private void showAllPages(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<PageEntity> pages = service.getAllEntities();

        req.setAttribute(Constants.PAGE_COUNT, pages.size());
        req.setAttribute(Constants.PAGES, pages);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/pages/list.jsp").forward(req, resp);
    }

    private PageEntity getPage(Long id, String title, String content) {

        PageEntity page = new PageEntity();

        page.setId(id);
        page.setTitle(title);
        page.setContent(content);

        return page;

    }

    private void prepareDefaultData(IPageService service) {
        PageEntity page;

        for (int i = 1; i <= 15; i++) {
            page = new PageEntity();
            page.setTitle(DEFAULT_TITLE + " " + i);
            page.setContent(DEFAULT_CONTENT + " " + i);

            Long id = service.create(page);

            page.setId(id);
            page.setTitle(DEFAULT_TITLE + " " + id);
            page.setContent(DEFAULT_CONTENT + " " + id);
            service.update(page);
        }
    }
}
