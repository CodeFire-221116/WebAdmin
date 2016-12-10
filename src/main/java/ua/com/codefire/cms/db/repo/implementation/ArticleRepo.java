package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Article;
import ua.com.codefire.cms.db.repo.abstraction.IArticleRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.12.2016.
 */
public class ArticleRepo implements IArticleRepo {
    private EntityManagerHelper entityManagerHelper;

    public ArticleRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    @Override
    public Long create(Article objToCreate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.persist(objToCreate);
            entityManagerHelper.commit();
            return  objToCreate.getId();
        } catch (EntityExistsException ex) {
            System.out.println("Such article already exists. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while creating new article. StackTrace:\n" + ex);
        } catch (Exception ex) {
            //entityManager.getTransaction().rollback();
            System.out.println("Unexpected exception, while creating new article. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public Article read(Long idToFind) {
        try {
            return entityManagerHelper.find(Article.class, idToFind);
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No article found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching for article. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching for article. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public Boolean update(Article objToUpdate) {
        try {
            entityManagerHelper.begin();
            entityManagerHelper.getEntityManager().merge(objToUpdate);
            entityManagerHelper.commit();
            return true;
        }  catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No such article found. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while updating the article. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while updating the article. StackTrace:\n" + ex);
        }
        return false;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        try {
            Article pageToDelete = entityManagerHelper.find(Article.class, objToDeleteId);
            entityManagerHelper.begin();
            entityManagerHelper.remove(pageToDelete);
            entityManagerHelper.commit();
            return true;
        } catch (EntityNotFoundException ex) {
            entityManagerHelper.rollback();
            System.out.println("No article found by such id. StackTrace:\n" + ex);
        } catch (PersistenceException ex){
            entityManagerHelper.rollback();
            System.out.println("Problems with database, while searching and deleting article. StackTrace:\n" + ex);
        } catch (Exception ex) {
            entityManagerHelper.rollback();
            System.out.println("Unexpected exception, while searching and deleting article. StackTrace:\n" + ex);
        }
        return null;
    }

    @Override
    public List<Article> getAllEntities() {
        List<Article> resultList = new ArrayList<>();
        try {
            Query query = entityManagerHelper.getEntityManager().createQuery("SELECT article FROM Article article", Article.class);
            resultList = (List<Article>) query.getResultList();
        } catch (ClassCastException ex) {
            System.out.println("Class casting problems, while retrieving articles from db. StackTrace:\n" + ex);
        } catch (PersistenceException ex) {
            System.out.println("Problems with db, while retrieving articles from db. StackTrace:\n" + ex);
        } catch (Exception ex) {
            System.out.println("Unexpected exception, while retrieving articles from db. StackTrace^\n" + ex);
        }
        return resultList;
    }
}
