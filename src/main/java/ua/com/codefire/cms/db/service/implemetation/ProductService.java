package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.ProductEntity;
import ua.com.codefire.cms.db.repo.abstraction.IProductRepo;
import ua.com.codefire.cms.db.repo.implementation.ProductRepo;
import ua.com.codefire.cms.db.service.abstraction.ICommonService;
import ua.com.codefire.cms.db.service.abstraction.IProductService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IProductService variable in case of need in entity-specific
 * methods and in the ICommonService(ProductEntity) variable in case of need in CRUD operations.
 */
public class ProductService implements IProductService {
    /**
     * IProductRepo variable, which holds a link to ProductRepo object in order to communicate with DataBase
     * with it`s help
     */
    private IProductRepo productRepo;

    /**
     * Creates a new instance of Service, intended to work with articles. Retrieves entity factory from request context
     * and creates a new repository, based on this factory, in order to communicate with DataBase
     * @param req HttpServletRequest instance, which can be retrieved in servlet`s methods.
     */
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
    public Long create(ProductEntity objToCreate) {
        return productRepo.create(objToCreate);
    }

    @Override
    public ProductEntity read(Long idToFind) {
        return productRepo.read(idToFind);
    }

    @Override
    public Boolean update(ProductEntity objToUpdate) {
        return productRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return productRepo.delete(objToDelete);
    }

    @Override
    public List<ProductEntity> getAllEntities() {
        return productRepo.getAllEntities();
    }

    @Override
    public Long getAmountOfEntities() {
        return productRepo.getAmountOfEntities();
    }
}
