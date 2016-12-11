package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.repo.abstraction.IUserRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by human on 12/6/16.
 */
public class UserRepo implements IUserRepo {
    private static final Logger LOGGER = Logger.getLogger(UserRepo.class.getName());
    private EntityManagerHelper entityManagerHelper;

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
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Such user already exists.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new user.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while creating new user.", ex);
        }
        return null;
    }

    @Override
    public UserEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(UserEntity.class, idToFind);
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
    public Boolean update(UserEntity objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No such user found.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while updating the user.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while updating the user.", ex);
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
            LOGGER.log(Level.SEVERE, "No user found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching and deleting user.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching and deleting user.", ex);
        }
        return null;
    }

    @Override
    public List<UserEntity> getAllEntities() {
        List<UserEntity> users = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT user FROM UserEntity user", UserEntity.class);
            users = (List<UserEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving users from db.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while retrieving all users from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving users from db.", ex);
        }
        return users;
    }

    @Override
    public UserEntity getUserByName(String name) {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT user FROM UserEntity user WHERE user.username = :userName", UserEntity.class);
            query.setParameter("userName", name);
            return (UserEntity) query.getSingleResult();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving user by name from db.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while retrieving user by name.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving user by name from db.", ex);
        }
        return null;
    }
}
