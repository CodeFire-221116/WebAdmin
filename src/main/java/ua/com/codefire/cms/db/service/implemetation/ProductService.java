package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Product;
import ua.com.codefire.cms.db.repo.abstraction.IProductRepo;
import ua.com.codefire.cms.db.repo.implementation.ProductRepo;
import ua.com.codefire.cms.db.service.abstraction.ICommonService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public class ProductService implements ICommonService<Product> {
    private IProductRepo productRepo;

    public ProductService(HttpServletRequest req) {
        Object factory = req.getServletContext().getAttribute("factory");
        if(factory != null) {
            try {
                productRepo = new ProductRepo(new EntityManagerHelper((EntityManagerFactory)factory));
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while generating product repository. StackTrace:\n" + ex);
            }
        }
    }

    @Override
    public Long create(Product objToCreate) {
        return productRepo.create(objToCreate);
    }

    @Override
    public Product read(Long idToFind) {
        return productRepo.read(idToFind);
    }

    @Override
    public Boolean update(Product objToUpdate) {
        return productRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return productRepo.delete(objToDelete);
    }

    @Override
    public List<Product> getAllEntities() {
        return productRepo.getAllEntities();
    }
}
