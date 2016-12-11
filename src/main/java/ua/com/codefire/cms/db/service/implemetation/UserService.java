package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.repo.abstraction.IUserRepo;
import ua.com.codefire.cms.db.repo.implementation.UserRepo;
import ua.com.codefire.cms.db.service.abstraction.IUserService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by human on 12/6/16.
 */
public class UserService implements IUserService {
    private IUserRepo userRepo;

    public UserService(ServletContext servletContext) {
        Object factory = servletContext.getAttribute("factory");
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

    public UserService(HttpServletRequest req) {
        this(req.getServletContext());
    }

    @Override
    public Long create(UserEntity objToCreate) {
        return userRepo.create(objToCreate);
    }

    @Override
    public UserEntity read(Long idToFind) {
        return userRepo.read(idToFind);
    }

    @Override
    public Boolean update(UserEntity objToUpdate) {
        return userRepo.update(objToUpdate);
    }

    @Override
    public Boolean delete(Long objToDelete) {
        return userRepo.delete(objToDelete);
    }

    @Override
    public List<UserEntity> getAllEntities() { return userRepo.getAllEntities(); }

    @Override
    public UserEntity getUserByName(String name) {
        return userRepo.getUserByName(name);
    }

    // INSERT INTO `test`.`users` VALUES ('pupkin', MD5('12345'));
    @Override
    public Boolean ifUserRegistered(String name, String password) {
        UserEntity userByName = userRepo.getUserByName(name);

        if (userByName == null) {
            return null;
        }

        return userByName.checkPassword(password);
    }
}
