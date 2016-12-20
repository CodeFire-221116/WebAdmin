package ua.com.codefire.cms.db.service.abstraction;

import java.util.List;

/**
 * Created by User on 07.12.2016.
 */

/**
 * @author ankys
 * @param <T> class of any Entity, presenting table in DataBase
 */
public interface ICommonService<T> {
    /**
     * Method which uses a database in order to create an incoming instance of any Entity class
     * @param objToCreate an instance of any Entity class, which will be added to DataBase
     * @return The id of object, added to DataBase or null in case of troubles, while trying to connect to DataBase
     */
    Long create(T objToCreate);

    /**
     * Method which uses a database in order to retrieve
     * an instance of any Entity class from DataBase by id in DataBase
     * @param idToFind An id of any Entity class instance, which needs to be retrieved from DataBase
     * @return An instance of any Entity class, retrieved by id or null in case of troubles,
     * while trying to connect to DataBase
     */
    T read(Long idToFind);

    /**
     * Method which uses a database in order to retrieve
     * an instance of any Entity class instance from DataBase by id in DataBase
     * @param objToUpdate instance of Generic class (any Entity), which needs to be updated in DataBase.
     *                    WARNING: instance needs to have an id
     * @return true if update was successful, false otherwise.
     * Used Wrapper in order to have an opportunity to return null in the future in case of need.
     */
    Boolean update(T objToUpdate);

    /**
     * Method which uses a database in order to retrieve an instance of any Entity class, which needs to be
     * deleted from DataBase by Id
     * @param objToDeleteId An id of any Entity class instance, which needs to be deleted from DataBase
     * @return true if delete was successful, false otherwise.
     * Used Wrapper in order to have an opportunity to return null in the future in case of need.
     */
    Boolean delete(Long objToDeleteId);

    /**
     * Method which retrieves an amount of rows of any Entity, presenting table, from DataBase
     * @return amount of rows in table, represented by Entity class
     */
    Long getAmountOfEntities();

    /**
     * Method which retrieves all rows of table, represented by Entity class.
     * @return List of instances of any Entity class, which represents a table in DataBase in case of success
     * Empty list in case of troubles while connecting to DB or empty table
     */
    List<T> getAllEntities();
}
