package ua.com.codefire.cms.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.cms.db.entity.ProductEntity;
import ua.com.codefire.cms.db.service.abstraction.IProductService;

import java.util.List;

/**
 * Created by User on 25.12.2016.
 */
@RequestMapping(path = "/admin/products")
@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

//    public ProductController() {
//    }
//
//    @Autowired
//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getProductsList(Model model) {
        List<ProductEntity> articles = productService.getAllEntities();

        model.addAttribute("productsList", articles);
        model.addAttribute("count", articles.size());

        return "admin/products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage() {
        return "/admin/products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateProduct(@RequestParam String type, @RequestParam String brand,
                                    @RequestParam String model, @RequestParam Double price) {
        productService.create(new ProductEntity(
                type,
                brand,
                model,
                price));
        return "redirect:/admin/products/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getProductEditPage(@RequestParam Long id, Model model) {
        ProductEntity productToEdit = productService.read(id);

        model.addAttribute("IDtoedit", id);
        model.addAttribute("TYPEtoedit", productToEdit.getType());
        model.addAttribute("BRANDtoedit", productToEdit.getBrand());
        model.addAttribute("MODELtoedit", productToEdit.getModel());
        model.addAttribute("PRICEtoedit", productToEdit.getPrice());

        return "admin/products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateProduct(@RequestParam Long id, @RequestParam String type, @RequestParam String brand,
                                    @RequestParam String model, @RequestParam Double price) {
        ProductEntity productToEdit = productService.read(id);
        productToEdit.setType(type);
        productToEdit.setBrand(brand);
        productToEdit.setModel(model);
        productToEdit.setPrice(price);
        productService.update(productToEdit);

        return "redirect:/admin/article/list";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteArticle(@RequestParam Long id) {
        productService.delete(id);

        return "redirect:/admin/article/list";
    }
}
