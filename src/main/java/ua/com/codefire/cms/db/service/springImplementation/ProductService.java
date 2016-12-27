package ua.com.codefire.cms.db.service.springImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Service;
import ua.com.codefire.cms.db.entity.ProductEntity;
import ua.com.codefire.cms.db.service.abstraction.IProductService;
import ua.com.codefire.cms.db.repo.ProductEntityRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * author ankys
 */
@Service
public class ProductService implements IProductService {
    private ProductEntityRepository productRepo;
    private static final Logger LOGGER = Logger.getLogger(ProductEntityRepository.class.getName());

    @Autowired
    public ProductService(ProductEntityRepository pageEntityRepository) {
        this.productRepo = pageEntityRepository;
    }

    @Override
    public Long create(ProductEntity objToCreate) {
        try {
            ProductEntity savedPage = productRepo.saveAndFlush(objToCreate);
            return savedPage.getId();
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such product already exists.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new product.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return null;
    }

    @Override
    public ProductEntity read(Long idToFind) {
        return productRepo.findOne(idToFind);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        try {
            productRepo.delete(objToDelete);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No product found by such id.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while deleting product.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception, while deleting product.", ex);
        }
        return false;
    }

    @Override
    public Boolean update(ProductEntity objToCreate) {
        try {
            productRepo.saveAndFlush(objToCreate);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No such product found.", ex);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while updating product.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return false;
    }

    @Override
    public List<ProductEntity> getAllEntities() {
        return productRepo.findAll();
    }

    @Override
    public Long getAmountOfEntities() {
        return productRepo.count();
    }
}
