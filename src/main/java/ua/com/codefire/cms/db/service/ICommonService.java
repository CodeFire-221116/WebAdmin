package ua.com.codefire.cms.db.service;

/**
 * Created by User on 07.12.2016.
 */
public interface ICommonService<T> {
    Integer create(T objToCreate);
    T read(int idToFind);
    Boolean update(T objToUpdate);
    Boolean delete(T objToCreate);
}
