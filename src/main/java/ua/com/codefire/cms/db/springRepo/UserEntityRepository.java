package ua.com.codefire.cms.db.springRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.ArticleEntity;

/**
 * Created by User on 27.12.2016.
 */
@Repository
public interface UserEntityRepository extends JpaRepository<ArticleEntity, Long> {
}