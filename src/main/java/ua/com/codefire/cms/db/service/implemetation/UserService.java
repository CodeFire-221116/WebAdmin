package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.User;
import ua.com.codefire.cms.db.repo.abstraction.IUserRepo;
import ua.com.codefire.cms.db.repo.implementation.UserRepo;
import ua.com.codefire.cms.db.service.abstraction.IUserService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by human on 12/6/16.
 */
public class UserService implements IUserService {
    private IUserRepo userRepo;

    public UserService(HttpServletRequest req) {
        Object factory = req.getServletContext().getAttribute("factory");
        if(factory != null) {
            try {
                userRepo = new UserRepo(new EntityManagerHelper((EntityManagerFactory)factory));
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while generating user repository. StackTrace:\n" + ex);
            }
        }
    }

    @Override
    public Long create(User objToCreate) {

        return userRepo.create(objToCreate);
    }

    @Override
    public User read(Long idToFind) {
        return userRepo.read(idToFind);
    }

    @Override
    public Boolean update(User objToUpdate) {
        return userRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return userRepo.delete(objToDelete);
    }

    @Override
    public List<User> getAllEntities() { return userRepo.getAllEntities(); }

    @Override
    public User getUserByCredentials(String name) {
        return userRepo.getUserByName(name);
    }
}
