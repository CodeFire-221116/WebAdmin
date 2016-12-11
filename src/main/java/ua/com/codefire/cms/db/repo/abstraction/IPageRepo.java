package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.PageEntity;

/**
 * Created by User on 07.12.2016.
 */
public interface IPageRepo extends ICommonRepo<PageEntity> {
    Long getAmountOfPages();
}
