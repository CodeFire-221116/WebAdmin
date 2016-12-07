package ua.com.codefire.cms.db.repo;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Product;

/**
 * Created by User on 07.12.2016.
 */
public class ProductRepo extends CommonRepo<Product> {
    public ProductRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    @Override
    public Long create(Product objToCreate) {
        return null;
    }

    @Override
    public Product read(Long idToFind) {
        return null;
    }

    @Override
    public Boolean update(Product objToUpdate) {
        return null;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        return null;
    }
}
