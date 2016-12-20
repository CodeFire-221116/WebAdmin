package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.PageEntity;
import ua.com.codefire.cms.db.repo.abstraction.IPageRepo;
import ua.com.codefire.cms.db.repo.implementation.PageRepo;
import ua.com.codefire.cms.db.service.abstraction.IPageService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
/**
 * An implementation of entity-specific Service Interface.
 * The object of this class needs to be put in the IPageService variable in case of need in entity-specific
 * methods and in the ICommonService(PageEntity) variable in case of need in CRUD operations.
 */
public class PageService implements IPageService {
    /**
     * IPageRepo variable, which holds a link to PageRepo object in order to communicate with DataBase
     * with it`s help
     */
    private IPageRepo pageRepo;

    /**
     * Creates a new instance of Service, intended to work with articles. Retrieves entity factory from request context
     * and creates a new repository, based on this factory, in order to communicate with DataBase
     * @param req HttpServletRequest instance, which can be retrieved in servlet`s methods.
     */
    public PageService(HttpServletRequest req) {
        Object factory = req.getServletContext().getAttribute("factory");
        if(factory != null) {
            try {
                pageRepo = new PageRepo(new EntityManagerHelper((EntityManagerFactory)factory));
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while generating page repository. StackTrace:\n" + ex);
            }
        }
    }

    @Override
    public Long create(PageEntity objToCreate) {
        return pageRepo.create(objToCreate);
    }

    @Override
    public PageEntity read(Long idToFind) {
        return pageRepo.read(idToFind);
    }

    @Override
    public Boolean update(PageEntity objToUpdate) {
        return pageRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return pageRepo.delete(objToDelete);
    }

    @Override
    public List<PageEntity> getAllEntities() {
        return pageRepo.getAllEntities();
    }

    @Override
    public Long getAmountOfEntities() {
        return pageRepo.getAmountOfEntities();
    }
}
