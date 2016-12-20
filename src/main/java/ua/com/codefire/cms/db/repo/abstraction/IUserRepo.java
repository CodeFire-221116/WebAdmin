package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by User on 07.12.2016.
 */
public interface IUserRepo extends ICommonRepo<UserEntity> {
    /**
     * Method to get an instance of UserEntity class from DataBase by user name,
     * using EntityManagerHelper, which provides thread-safe connection
     * @param name Unique parameter which will be used to find user in DataBase
     * @return An instance of UserEntity class, found in DataBase by name or
     * null in case of troubles while connecting to DataBase
     */
    UserEntity getUserByName(String name);
    Boolean sendValidationEmail(Long id);
    Boolean validateEmail(String validationCode);
}
