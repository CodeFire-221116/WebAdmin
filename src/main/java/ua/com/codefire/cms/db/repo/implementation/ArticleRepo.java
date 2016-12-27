package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.repo.abstraction.IArticleRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 10.12.2016.
 */
/**
 * An implementation of entity-specific Repository Interface.
 * The object of this class needs to be put in the IArticleRepo variable in case of need in entity-specific
 * methods and in the ICommonRepo(ArticleEntity) variable in case of need in CRUD operations.
 * Used in ArticleService in order to communicate with DataBase
 * @deprecated  As of release 1.3, replaced by {@link ua.com.codefire.cms.db.springRepo.ArticleEntityRepository}
 */
public class ArticleRepo implements IArticleRepo {
    private static final Logger LOGGER = Logger.getLogger(ArticleRepo.class.getName());
    private EntityManagerHelper entityManagerHelper;

    public ArticleRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public Long create(ArticleEntity objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Such article already exists.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new article.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while creating new article.", ex);
        }
        return null;
    }

    @Override
    public ArticleEntity read(Long idToFind) {
        try {
            return entityManagerHelper.find(ArticleEntity.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No article found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching for article.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching for article.", ex);
        }
        return null;
    }

    @Override
    public Boolean update(ArticleEntity objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No such article found.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while updating the article.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while updating the article.", ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            ArticleEntity pageToDelete = entityManagerHelper.find(ArticleEntity.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(pageToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "No article found by such id.", ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with database, while searching and deleting article.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while searching and deleting article.", ex);
        }
        return null;
    }

    @Override
    public List<ArticleEntity> getAllEntities() {
        List<ArticleEntity> articles = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT article FROM ArticleEntity article", ArticleEntity.class);
            articles = (List<ArticleEntity>) query.getResultList();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving articles from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving articles from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving articles from db.", ex);
        }
        return articles;
    }

    @Override
    public Long getAmountOfEntities() {
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT COUNT(article.id) FROM ArticleEntity article", Long.class);
            return (Long) query.getSingleResult();
        } catch (ClassCastException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Class casting problems, while retrieving amount of articles from db.", ex);
        } catch (PersistenceException ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Problems with db, while retrieving amount of articles from db.", ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            LOGGER.log(Level.SEVERE, "Unexpected exception, while retrieving amount of articles from db.", ex);
        }
        return null;
    }
}
