package ua.com.codefire.cms.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.ProductEntity;

/**
 * Created by User on 27.12.2016.
 */
@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
}
