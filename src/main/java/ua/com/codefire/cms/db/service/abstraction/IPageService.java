package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.PageEntity;

/**
 * Created by User on 07.12.2016.
 */
public interface IPageService extends ICommonService<PageEntity> {
    Long getAmountOfPAges();
}
