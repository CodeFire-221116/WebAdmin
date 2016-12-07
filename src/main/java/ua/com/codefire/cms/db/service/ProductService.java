package ua.com.codefire.cms.db.service;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Product;
import ua.com.codefire.cms.db.repo.ProductRepo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 07.12.2016.
 */
public class ProductService extends CommonService<Product> {
    public ProductService(HttpServletRequest req) {
        setFactoryFromRequest(req);
        commonRepo = new ProductRepo(new EntityManagerHelper(factory));
    }

    @Override
    public Long create(Product objToCreate) {
        return commonRepo.create(objToCreate);
    }

    @Override
    public Product read(Long idToFind) {
        return commonRepo.read(idToFind);
    }

    @Override
    public Boolean update(Product objToUpdate) {
        return commonRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return commonRepo.delete(objToDelete);
    }
}
