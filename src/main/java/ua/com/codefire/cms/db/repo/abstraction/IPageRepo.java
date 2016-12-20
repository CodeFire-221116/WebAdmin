package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.PageEntity;

/**
 * Created by User on 07.12.2016.
 */
/**
 * Entity-specific interface, which extends ICommonRepo with PageEntity as generic class.
 * Created to add entity-specific methods
 * @author ankys
 */
public interface IPageRepo extends ICommonRepo<PageEntity> {
}
