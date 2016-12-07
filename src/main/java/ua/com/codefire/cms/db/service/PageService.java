package ua.com.codefire.cms.db.service;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.Page;
import ua.com.codefire.cms.db.repo.PageRepo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 07.12.2016.
 */
public class PageService extends CommonService<Page> {
    public PageService(HttpServletRequest req) {
        setFactoryFromRequest(req);
        commonRepo = new PageRepo (new EntityManagerHelper(factory));
    }

    @Override
    public Long create(Page objToCreate) {
        return commonRepo.create(objToCreate);
    }

    @Override
    public Page read(Long idToFind) {
        return commonRepo.read(idToFind);
    }

    @Override
    public Boolean update(Page objToUpdate) {
        return commonRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return commonRepo.delete(objToDelete);
    }
}
