package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by USER on 18.12.2016.
 */
public interface IUserReg {
    void add(UserEntity user);
    boolean isExist(UserEntity user);
}
