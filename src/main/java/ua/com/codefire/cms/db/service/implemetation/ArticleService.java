package ua.com.codefire.cms.db.service.implemetation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.repo.abstraction.IArticleRepo;
import ua.com.codefire.cms.db.repo.implementation.ArticleRepo;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;
import ua.com.codefire.cms.db.springRepo.ArticleEntityRepository;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IArticleService variable in case of need in entity-specific
 * methods and in the ICommonService(ArticleEntity) variable in case of need in CRUD operations.
 * @deprecated  As of release 1.3, replaced by {@link ua.com.codefire.cms.db.service.springImplementation.ArticleService}
 * @author ankys
 */
@Deprecated
public class ArticleService implements IArticleService {
    /**
     * IArticleRepo variable, which holds a link to ArticleRepo object in order to communicate with DataBase
     * with it`s help
     */
    private IArticleRepo articleRepo;

    /**
     * Creates a new instance of Service, intended to work with articles. Retrieves entity factory from request context
     * and creates a new repository, based on this factory, in order to communicate with DataBase
     * @param req HttpServletRequest instance, which can be retrieved in servlet`s methods.
     */
    public ArticleService(HttpServletRequest req) {
        Object factory = req.getServletContext().getAttribute("factory");
        if(factory != null) {
            try {
                articleRepo = new ArticleRepo(new EntityManagerHelper((EntityManagerFactory)factory));
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while generating article repository. StackTrace:\n" + ex);
            }
        }
    }
    @Override
    public Long create(ArticleEntity objToCreate) {
        return articleRepo.create(objToCreate);
    }

    @Override
    public ArticleEntity read(Long idToFind) {
        return articleRepo.read(idToFind);
    }

    @Override
    public Boolean update(ArticleEntity objToUpdate) {
        return articleRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return articleRepo.delete(objToDelete);
    }

    @Override
    public List<ArticleEntity> getAllEntities() {
        return articleRepo.getAllEntities();
    }

    @Override
    public Long getAmountOfEntities() {
        return articleRepo.getAmountOfEntities();
    }
}
