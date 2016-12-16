package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.UserEntity;
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
        if (factory != null) {
            try {
                userRepo = new UserRepo(new EntityManagerHelper((EntityManagerFactory) factory));
            } catch (ClassCastException ex) {
                System.out.println("Wrong parameter put into context. StackTrace:\n" + ex);
            } catch (Exception ex) {
                System.out.println("Unexpected exception, while generating user repository. StackTrace:\n" + ex);
            }
        }
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
    public List<UserEntity> getAllEntities() {
        return userRepo.getAllEntities();
    }

    @Override
    public UserEntity getUserByName(String name) {
        return userRepo.getUserByName(name);
    }

    // INSERT INTO `test`.`users` VALUES ('pupkin', MD5('12345'));
    @Override
    public Boolean ifUserRegistered(String name, String password) {
        if (name == null) {
            return null;
        }

        UserEntity userByName = userRepo.getUserByName(name);

        if (userByName == null) {
            return null;
        }

        if (password == null) {
            return false;
        }

        return userByName.checkPassword(password);
    }

    @Override
    public Boolean sendValidationEmail(Long id) {
        return userRepo.sendValidationEmail(id);
    }

    @Override
    public Boolean validateEmail(Long id, Long key) {
        return userRepo.validateEmail(id, key);
    }

    @Override
    public Long getAmountOfEntities() {
        return userRepo.getAmountOfEntities();
    }
}
