package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by USER on 18.12.2016.
 */
public interface IUserRegServ{

    boolean isRegistered(UserEntity user);
    void register(UserEntity user);
}
