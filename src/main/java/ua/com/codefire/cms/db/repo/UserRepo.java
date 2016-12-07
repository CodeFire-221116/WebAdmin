package ua.com.codefire.cms.db.repo;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Page;
import ua.com.codefire.cms.db.entity.User;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

/**
 * Created by human on 12/6/16.
 */
public class UserRepo extends CommonRepo<User> {
    public UserRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    @Override
    public Long create(User objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            System.out.println("Such user already exists. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while creating new user. StackTrace:\n" + ex);
        } catch (Exception ex) {
            //entityManager.getTransaction().rollback();
            System.out.println("Unexpected exception, while creating new user. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public User read(Long idToFind) {
        try {
            return entityManagerHelper.find(User.class, idToFind);
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
    public Boolean update(User objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No such user found. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while updating the user. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while updating the user. StackTrace:\n" + ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            User userToDelete = entityManagerHelper.find(User.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(userToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No user found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching and deleting user. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching and deleting user. StackTrace:\n" + ex);
        }
        return null;
    }
}
