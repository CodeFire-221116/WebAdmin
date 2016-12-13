package ua.com.codefire.cms.servlet.admin;

import ua.com.codefire.cms.db.entity.ProductEntity;
import ua.com.codefire.cms.db.service.implemetation.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Katya on 01.12.2016.
 */
@WebServlet("/admin/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && "new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/admin/products/edit.jsp").forward(req, resp);
        } else {
            Long id = null;
            if (req.getParameter("id") != null) {
                id = Long.parseLong(req.getParameter("id"));
            }

            List<ProductEntity> products = new ProductService(req).getAllEntities();

            if (!products.isEmpty()) {
                req.setAttribute("productsList", products);
                req.setAttribute("count", products.size());

                for (ProductEntity product : products) {
                    if (id != null && id.equals(product.getId())) {
                        req.setAttribute("IDtoedit", id);
                        req.setAttribute("TYPEtoedit", product.getType());
                        req.setAttribute("BRANDtoedit", product.getBrand());
                        req.setAttribute("MODELtoedit", product.getModel());
                        req.setAttribute("PRICEtoedit", product.getPrice());

                        req.getRequestDispatcher("/WEB-INF/jsp/admin/products/edit.jsp").forward(req, resp);
                        return;
                    }
                }
            }

            req.getRequestDispatcher("/WEB-INF/jsp/admin/products/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null && "new".equals(action)) {
            ProductService newProduct = new ProductService(req);
            newProduct.update(new ProductEntity(
                    req.getParameter("productType"),
                    req.getParameter("productBrand"),
                    req.getParameter("productModel"),
                    Double.parseDouble(req.getParameter("productPrice"))));
        } else {
            if (action != null && "delete".equals(action)) {


            } else {
                Long id = null;
                if (req.getParameter("id") != null) {
                    id = Long.parseLong(req.getParameter("id"));
                }

                if (id != null) {
                    ProductService newProduct = new ProductService(req);
                    newProduct.update(new ProductEntity(
                            id,
                            req.getParameter("productType"),
                            req.getParameter("productBrand"),
                            req.getParameter("productModel"),
                            Double.parseDouble(req.getParameter("productPrice"))));
                }
            }
        }
        resp.sendRedirect("/admin/products");
    }
}
