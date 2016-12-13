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
public class PageService implements IPageService {
    private IPageRepo pageRepo;

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
