package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.Page;

/**
 * Created by User on 07.12.2016.
 */
public interface IPageService extends CommonService<Page> {
    int getAmountOfPAges();
}
