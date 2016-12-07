package ua.com.codefire.cms.db.repo;

import ua.com.codefire.cms.db.configs.EntityManagerHelper;

/**
 * Created by User on 07.12.2016.
 */
public abstract class CommonRepo <T> {
    EntityManagerHelper entityManagerHelper;

    public abstract Long create(T objToCreate);
    public abstract T read(Long idToFind);
    public abstract Boolean update(T objToUpdate);
    public abstract Boolean delete(Long objToDeleteId);
}
