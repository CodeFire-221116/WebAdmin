package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.ArticleEntity;

/**
 * Created by User on 10.12.2016.
 */
/**
 * Entity-specific interface, which extends ICommonService with ArticleEntity as generic class.
 * Created to add entity-specific methods
 * @author ankys
 */
public interface IArticleService extends ICommonService<ArticleEntity> {
}
