package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.User;

import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public interface IUserService extends CommonService<User> {
    User getUserByCredentials(String name);
}
