package ua.com.codefire.cms.db.repo.abstraction;

import ua.com.codefire.cms.db.entity.ProductEntity;

/**
 * Created by User on 07.12.2016.
 */
/**
 * Entity-specific interface, which extends ICommonRepo with ProductEntity as generic class.
 * Created to add entity-specific methods
 * @author ankys
 */
public interface IProductRepo extends ICommonRepo<ProductEntity> {

}
