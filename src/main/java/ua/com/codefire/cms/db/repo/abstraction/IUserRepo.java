package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by User on 07.12.2016.
 */
public interface IUserRepo extends ICommonRepo<UserEntity> {
    UserEntity getUserByName(String name);
    Boolean sendValidationEmail(Long id);
    Boolean validateEmail(Long id, Long key);
}
