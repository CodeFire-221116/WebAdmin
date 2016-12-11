package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;

import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public interface ICommonRepo<T> {
    Long create(T objToCreate);
    T read(Long idToFind);
    Boolean update(T objToUpdate);
    Boolean delete(Long objToDeleteId);
    Long getAmountOfEntities();
    List<T> getAllEntities();
}
