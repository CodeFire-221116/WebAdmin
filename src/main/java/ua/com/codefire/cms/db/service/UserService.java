package ua.com.codefire.cms.db.service;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.User;
import ua.com.codefire.cms.db.repo.UserRepo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by human on 12/6/16.
 */
public class UserService extends CommonService<User> {
    public UserService(HttpServletRequest req) {
        setFactoryFromRequest(req);
        commonRepo = new UserRepo(new EntityManagerHelper(factory));
    }
    @Override
    public Long create(User objToCreate) {
        return commonRepo.create(objToCreate);
    }

    @Override
    public User read(Long idToFind) {
        return commonRepo.read(idToFind);
    }

    @Override
    public Boolean update(User objToUpdate) {
        return commonRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return commonRepo.delete(objToDelete);
    }
}
