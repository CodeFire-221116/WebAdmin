package ua.com.codefire.cms.db.service.abstraction;

import ua.com.codefire.cms.db.entity.ProductEntity;

/**
 * Created by User on 07.12.2016.
 */

/**
 * Entity-specific interface, which extends ICommonService with ProductEntity as generic class.
 * Created to add entity-specific methods
 * @author ankys
 */
public interface IProductService extends ICommonService<ProductEntity> {
}
