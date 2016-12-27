package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by USER on 18.12.2016.
 * @deprecated  As of release 1.3, replaced by {@link ua.com.codefire.cms.db.service.springImplementation.UserService}
 * Not required, because these methods exist in UserService, linked higher
 */
public interface IUserRegServ{

    boolean isRegistered(UserEntity user);
    void register(UserEntity user);
}
