package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.repo.abstraction.IUserRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by human on 12/6/16.
 */
public class UserRepo implements IUserRepo {
    EntityManagerHelper entityManagerHelper;

    public UserRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    @Override
    public Long create(UserEntity objToCreate) {
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
    public UserEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(UserEntity.class, idToFind);
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
    public Boolean update(UserEntity objToUpdate) {
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
            UserEntity userToDelete = entityManagerHelper.find(UserEntity.class, objToDeleteId);
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

    @Override
    public List<UserEntity> getAllEntities() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT user FROM UserEntity user", UserEntity.class);
            return (List<UserEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            System.out.println("Class casting problems, while retrieving users from db. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while retrieving all users from db. StackTrace:\n" + ex);
        } catch (Exception ex) {
            System.out.println("Unexpected exception, while retrieving users from db. StackTrace^\n" + ex);
        }
        return null;
    }

    @Override
    public UserEntity getUserByName(String name) {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT user FROM UserEntity user WHERE user.username = :userName", UserEntity.class);
            query.setParameter("userName", name);
            return (UserEntity) query.getSingleResult();
        } catch (ClassCastException ex) {
            System.out.println("Class casting problems, while retrieving user by name from db. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while retrieving user by name. StackTrace:\n" + ex);
        } catch (Exception ex) {
            System.out.println("Unexpected exception, while retrieving user by name from db. StackTrace^\n" + ex);
        }
        return null;
    }
}
