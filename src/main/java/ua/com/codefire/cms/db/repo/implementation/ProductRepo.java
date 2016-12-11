package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.ProductEntity;
import ua.com.codefire.cms.db.repo.abstraction.IProductRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 07.12.2016.
 */
public class ProductRepo implements IProductRepo {
    private static final Logger LOGGER = Logger.getLogger(ProductRepo.class.getName());
    private EntityManagerHelper entityManagerHelper;

    public ProductRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    @Override
    public Long create(ProductEntity objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such product already exists.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new product.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while creating new product.", ex);
        }
        return null;
    }

    @Override
    public ProductEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(ProductEntity.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No product found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching for product.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching for product.", ex);
        }
        return null;
    }

    @Override
    public Boolean update(ProductEntity objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No such product found.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while updating the product.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while updating the product.", ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            ProductEntity pageToDelete = entityManagerHelper.find(ProductEntity.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(pageToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No product found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching and deleting product.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching and deleting product.", ex);
        }
        return null;
    }

    @Override
    public List<ProductEntity> getAllEntities() {
        List<ProductEntity> products = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT product FROM ProductEntity product", ProductEntity.class);
            products = (List<ProductEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving products from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving products from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving products from db.", ex);
        }
        return products;
    }

    @Override
    public Long getAmountOfEntities() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT COUNT(product.id) FROM ProductEntity product", Long.class);
            return (Long) query.getSingleResult();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving amount of products from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving amount of products from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving amount of products from db.", ex);
        }
        return null;
    }
}
