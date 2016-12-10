package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.repo.abstraction.IPageRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public class PageRepo implements IPageRepo {
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
            System.out.println("Such page already exists. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while creating new page. StackTrace:\n" + ex);
        } catch (Exception ex) {
            //entityManager.getTransaction().rollback();
            System.out.println("Unexpected exception, while creating new page. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public PageEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(PageEntity.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No page found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching for page. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching for page. StackTrace:\n" + ex);
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
            System.out.println("No such page found. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while updating the page. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while updating the page. StackTrace:\n" + ex);
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
            System.out.println("No page found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching and deleting page. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching and deleting page. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public List<PageEntity> getAllEntities() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT page FROM PageEntity page", PageEntity.class);
            return (List<PageEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            System.out.println("Class casting problems, while retrieving pages from db. StackTrace:\n" + ex);
        } catch (PersistenceException ex) {
            System.out.println("Problems with db, while retrieving pages from db. StackTrace:\n" + ex);
        } catch (Exception ex) {
            System.out.println("Unexpected exception, while retrieving pages from db. StackTrace^\n" + ex);
        }
        return null;
    }

    @Override
    public Long getAmountOfPages() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT COUNT(page.id) FROM PageEntity page", Long.class);
            return (Long) query.getSingleResult();
        } catch (ClassCastException ex) {
            System.out.println("Class casting problems, while retrieving amount of pages from db. StackTrace:\n" + ex);
        } catch (PersistenceException ex) {
            System.out.println("Problems with db, while retrieving amount of pages from db. StackTrace:\n" + ex);
        } catch (Exception ex) {
            System.out.println("Unexpected exception, while retrieving amount of pages from db. StackTrace^\n" + ex);
        }
        return null;
    }
}
