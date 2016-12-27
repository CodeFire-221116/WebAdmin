package ua.com.codefire.cms.db.springRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.PageEntity;

/**
 * Created by User on 27.12.2016.
 */
@Repository
public interface PageEntityRepository extends JpaRepository<PageEntity, Long> {
}
