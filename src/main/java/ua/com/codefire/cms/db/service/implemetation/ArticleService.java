package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.repo.abstraction.IArticleRepo;
import ua.com.codefire.cms.db.repo.implementation.ArticleRepo;
import ua.com.codefire.cms.db.service.abstraction.IArticleService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 10.12.2016.
 */
public class ArticleService implements IArticleService {
    private IArticleRepo articleRepo;

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
