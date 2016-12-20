package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by User on 07.12.2016.
 */
/**
 * Entity-specific interface, which extends ICommonService with UserEntity as generic class.
 * Created to add entity-specific methods
 * @author ankys
 */
public interface IUserService extends ICommonService<UserEntity> {
    /**
     * Method to get an instance of UserEntity class from DataBase by user name
     * @param name Unique parameter which will be used to find user in DataBase
     * @return An instance of UserEntity class, found in DataBase by name or
     * null in case of troubles while connecting to DataBase
     */
    UserEntity getUserByName(String name);

    /**
     * Method to define, if user is Registered and his password is correct.
     * @param name Name of User to check for registration
     * @param password Password of User to check for registration
     * @return true, if user is registered, false - otherwise.
     * Used Wrapper in order to have an opportunity to return null in the future in case of need.
     */
    Boolean ifUserRegistered(String name, String password);

    /**
     * Method to send an Email to user`s email address in order to check if this email is valid
     * @param id An id of user, whose email needs to be checked
     * @return true, if email was sent successful, false in case of troubles, while retrieving user or while sending message
     */
    Boolean sendValidationEmail(Long id);

    /**
     * Method to validate email in case of response to validation Email. Not implemented yet
     * @param id An id of user, whose email need to be validated
     * @param key Unique parameter, which is sent by user while validating email
     * @return true in case of successful validation, false otherwise.
     */
    Boolean validateEmail(Long id, Long key);
}
