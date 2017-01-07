package ua.com.codefire.cms.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    public ProductController() {
    }

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getProductsList(Model model) {
        List<ProductEntity> products = productService.getAllEntities();

        model.addAttribute("productsList", products);
        model.addAttribute("count", products.size());

        return "admin/products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage() {
        return "/admin/products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateProduct(@ModelAttribute ProductEntity productEntity) {
        productService.create(productEntity);
        return "redirect:/admin/products/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getProductEditPage(@RequestParam Long id, Model model) {
        ProductEntity productToEdit = productService.read(id);

        model.addAttribute("IDtoedit", id);
        model.addAttribute("TYPEtoedit", productToEdit.getProductType());
        model.addAttribute("BRANDtoedit", productToEdit.getProductBrand());
        model.addAttribute("MODELtoedit", productToEdit.getProductModel());
        model.addAttribute("PRICEtoedit", productToEdit.getProductPrice());

        return "admin/products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateProduct(@RequestParam Long id, @RequestParam String productType, @RequestParam String productBrand,
                                    @RequestParam String productModel, @RequestParam Double productPrice) {
        ProductEntity productToEdit = productService.read(id);
        productToEdit.setProductType(productType);
        productToEdit.setProductBrand(productBrand);
        productToEdit.setProductModel(productModel);
        productToEdit.setProductPrice(productPrice);
        try {
            productService.update(productToEdit);
        } catch (NullPointerException ex){

        }
        return "redirect:/admin/products/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteProduct(@RequestParam Long id) {
        productService.delete(id);

        return "redirect:/admin/products/";
    }
}
