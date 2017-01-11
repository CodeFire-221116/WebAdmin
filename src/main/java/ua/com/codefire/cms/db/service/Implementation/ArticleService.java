package ua.com.codefire.cms.db.service.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Service;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.repo.ArticleEntityRepository;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IArticleService variable in case of need in entity-specific
 * methods and in the ICommonService(ArticleEntity) variable in case of need in CRUD operations.
 * The creation has to be with the help of @Autowired annotation
 * @author ankys
 */
@Service
public class ArticleService implements IArticleService {
    private static final Logger LOGGER = Logger.getLogger(ArticleEntityRepository.class.getName());

    /**
     * ArticleEntityRepository variable, which is a Spring Data Interface, intended to work with DataBase
     * with it`s help
     */
    @Autowired
    private ArticleEntityRepository articleRepo;

    @Override
    public Long create(ArticleEntity objToCreate) {
        try {
            ArticleEntity savedArticle = articleRepo.saveAndFlush(objToCreate);
            return savedArticle.getId();
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such article already exists.", ex);
        } catch (PersistenceException ex) {
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new article.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return null;
    }

    @Override
    public ArticleEntity read(Long idToFind) {
        return articleRepo.findOne(idToFind);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        try {
            articleRepo.delete(objToDelete);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No article found by such id.", ex);
        } catch (PersistenceException ex) {
            LOGGER.log(Level.SEVERE, "Problems with database, while deleting article.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception, while deleting article.", ex);
        }
        return false;
    }

    @Override
    public Boolean update(ArticleEntity objToCreate) {
        try {
            articleRepo.saveAndFlush(objToCreate);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No such article found.", ex);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (PersistenceException ex) {
            LOGGER.log(Level.SEVERE, "Problems with database, while updating article.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return false;
    }

    @Override
    public List<ArticleEntity> getAllEntities() {
        return articleRepo.findAll();
    }

    @Override
    public Long getAmountOfEntities() {
        return articleRepo.count();
    }
}
