package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by User on 07.12.2016.
 */
public interface IUserService extends ICommonService<UserEntity> {
    UserEntity getUserByName(String name);
    Boolean ifUserRegistered(String name, String password);
    Boolean sendValidationEmail(Long id);
    Boolean validateEmail(Long id, Long key);
}
