package ua.com.codefire.cms.db.repo;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;
import ua.com.codefire.cms.db.entity.User;

/**
 * Created by human on 12/6/16.
 */
public class UserRepo extends CommonRepo<User> {
    public UserRepo(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    @Override
    public Long create(User objToCreate) {
        return null;
    }

    @Override
    public User read(Long idToFind) {
        return null;
    }

    @Override
    public Boolean update(User objToUpdate) {
        return null;
    }

    @Override
    public Boolean delete(Long objToDeleteId) {
        return null;
    }
}
