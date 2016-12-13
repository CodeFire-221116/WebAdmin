package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.repo.abstraction.IPageRepo;

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
public class PageRepo implements IPageRepo {
    private static final Logger LOGGER = Logger.getLogger(PageRepo.class.getName());
    private EntityManagerHelper entityManagerHelper;

    public PageRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public Long create(PageEntity objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Such page already exists.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new page.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while creating new page.", ex);
        }
        return null;
    }

    @Override
    public PageEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(PageEntity.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No page found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching for page.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching for page.", ex);
        }
        return null;
    }

    @Override
    public Boolean update(PageEntity objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No such page found.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while updating the page.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while updating the page.", ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            PageEntity pageToDelete = entityManagerHelper.find(PageEntity.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(pageToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No page found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching and deleting page.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching and deleting page.", ex);
        }
        return null;
    }

    @Override
    public List<PageEntity> getAllEntities() {
        List<PageEntity> pages = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT page FROM PageEntity page", PageEntity.class);
            pages = (List<PageEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving pages from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving pages from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving pages from db.", ex);
        }
        return pages;
    }

    @Override
    public Long getAmountOfEntities() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT COUNT(page.id) FROM PageEntity page", Long.class);
            return (Long) query.getSingleResult();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving amount of pages from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving amount of pages from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving amount of pages from db.", ex);
        }
        return null;
    }
}
