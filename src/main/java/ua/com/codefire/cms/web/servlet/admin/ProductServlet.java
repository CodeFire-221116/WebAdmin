package ua.com.codefire.cms.web.servlet.admin;

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
    private StringBuilder errors;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        req.setAttribute("action", action);

        if (action != null && "new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/admin/products/edit.jsp").forward(req, resp);
        } else {
            if (req.getParameter("id") != null) {
                setAttributesToEdit(req, Long.parseLong(req.getParameter("id")));
                req.getRequestDispatcher("/WEB-INF/jsp/admin/products/edit.jsp").forward(req, resp);
                return;
            }
            List<ProductEntity> products = new ProductService(req).getAllEntities();
            if (!products.isEmpty()) {
                req.setAttribute("productsList", products);
                req.setAttribute("count", products.size());
            }
            req.getRequestDispatcher("/WEB-INF/jsp/admin/products/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductService(req);
        errors = new StringBuilder();
        String action = req.getParameter("action");
        String buttonValue = req.getParameter("button");

        if (action != null && "new".equals(action)) {

            req.setAttribute("action", action);
            setAttributesToCreate(req);
            tryToCreateEntity(req, productService);

        } else if (req.getParameter("id") != null) {

            Long id = Long.parseLong(req.getParameter("id"));
            setAttributesToEdit(req, id);

            if (buttonValue != null) {
                if ("Apply".equals(buttonValue)) {
                    tryToUpdateEntity(req, id, productService);
                } else if ("Delete".equals(buttonValue)) {
                    productService.delete(id);
                }
            }
        }
        if ("Back".equals(buttonValue)){
            resp.sendRedirect("/admin/products");
            return;
        } else if (!errors.toString().isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/jsp/admin/products/edit.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/admin/products");
    }

    private void setAttributesToEdit(HttpServletRequest req, Long id) {
        ProductEntity product = new ProductService(req).read(id);
        req.setAttribute("IDtoedit", id);
        req.setAttribute("TYPEtoedit", product.getType());
        req.setAttribute("BRANDtoedit", product.getBrand());
        req.setAttribute("MODELtoedit", product.getModel());
        req.setAttribute("PRICEtoedit", product.getPrice());
    }
    private void setAttributesToCreate(HttpServletRequest req) {
        req.setAttribute("TYPEtoedit", req.getParameter("productType"));
        req.setAttribute("BRANDtoedit", req.getParameter("productBrand"));
        req.setAttribute("MODELtoedit", req.getParameter("productModel"));
        req.setAttribute("PRICEtoedit", req.getParameter("productPrice"));
    }
    private void tryToCreateEntity(HttpServletRequest req, ProductService productService) {
        String type = req.getParameter("productType");
        String brand = req.getParameter("productBrand");
        String model = req.getParameter("productModel");
        String price = req.getParameter("productPrice");

        String[] result = price.split("\\.");
        String resultPrice;
        if(result.length < 2){
            resultPrice = result[0].replaceAll("\\D", "");
        } else {
            resultPrice = result[0].replaceAll("\\D", "") + "." + result[1].replaceAll("\\D", "");
        }
        if (model != null && !model.trim().isEmpty()) {
            try {
                productService.create(new ProductEntity(type, brand, model, Double.parseDouble(resultPrice)));
            } catch (NumberFormatException e) {
                errors.append("Enter the price of the product");
            } catch (Exception e) {
                errors.append("Exception because of wrong value");
            }
        } else {
            errors.append("Enter the model of the product");
        }
    }

    private void tryToUpdateEntity(HttpServletRequest req, Long id, ProductService productService) {
        String type = req.getParameter("productType");
        String brand = req.getParameter("productBrand");
        String model = req.getParameter("productModel");
        String price = req.getParameter("productPrice");

        String[] result = price.split("\\.");
        String resultPrice;
        if(result.length < 2){
            resultPrice = result[0].replaceAll("\\D", "");
        } else {
            resultPrice = result[0].replaceAll("\\D", "") + "." + result[1].replaceAll("\\D", "");
        }
        if (model != null && !model.trim().isEmpty()) {
            try {
                productService.update(new ProductEntity(id, type, brand, model, Double.parseDouble(resultPrice)));
            } catch (NumberFormatException e) {
                errors.append("Enter the price of the product");
            } catch (Exception e) {
                errors.append("Exception because of wrong price value");
            }
        } else {
            errors.append("Enter the model of the product");
        }
    }
}
