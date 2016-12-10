package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.BookEntity;
import ua.com.codefire.cms.db.repo.abstraction.IBookRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.12.2016.
 */
public class BookRepo implements IBookRepo {
    private EntityManagerHelper entityManagerHelper;

    public BookRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public Long create(BookEntity objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            System.out.println("Such book already exists. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while creating new book. StackTrace:\n" + ex);
        } catch (Exception ex) {
            //entityManager.getTransaction().rollback();
            System.out.println("Unexpected exception, while creating new book. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public BookEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(BookEntity.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No book found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching for book. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching for book. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public Boolean update(BookEntity objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No such book found. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while updating the book. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while updating the book. StackTrace:\n" + ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            BookEntity pageToDelete = entityManagerHelper.find(BookEntity.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(pageToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No book found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching and deleting book. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching and deleting book. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public List<BookEntity> getAllEntities() {
        List<BookEntity> resultList = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT book FROM BookEntity book", BookEntity.class);
            resultList = (List<BookEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            System.out.println("Class casting problems, while retrieving books from db. StackTrace:\n" + ex);
        } catch (PersistenceException ex) {
            System.out.println("Problems with db, while retrieving books from db. StackTrace:\n" + ex);
        } catch (Exception ex) {
            System.out.println("Unexpected exception, while retrieving books from db. StackTrace^\n" + ex);
        }
        return resultList;
    }
}
