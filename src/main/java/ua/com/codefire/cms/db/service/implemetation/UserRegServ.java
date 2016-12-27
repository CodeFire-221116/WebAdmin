package ua.com.codefire.cms.db.service.implemetation;

import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.repo.abstraction.IUserReg;
import ua.com.codefire.cms.db.repo.implementation.UserReg;
import ua.com.codefire.cms.db.service.abstraction.IUserRegServ;

/**
 * Created by USER on 18.12.2016.
 * @deprecated  As of release 1.3, replaced by {@link ua.com.codefire.cms.db.service.springImplementation.UserService}
 * Not required, because these methods exist in UserService
 */
public class UserRegServ implements IUserRegServ {

    private final IUserReg userReg = new UserReg();

    @Override
    public void register(UserEntity user) {
        userReg.add(user);
    }

    @Override
    public boolean isRegistered(UserEntity user) {
        return userReg.isExist(user);
    }
}
