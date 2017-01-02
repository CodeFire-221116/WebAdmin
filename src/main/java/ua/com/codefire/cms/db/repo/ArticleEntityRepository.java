package ua.com.codefire.cms.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.ArticleEntity;

/**
 * Created by User on 26.12.2016.
 */
@Repository
public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Long> {
}
