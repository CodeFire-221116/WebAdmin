package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.User;

/**
 * Created by User on 07.12.2016.
 */
public interface IUserRepo extends ICommonRepo<User> {
    User getUserByName(String name);
}
