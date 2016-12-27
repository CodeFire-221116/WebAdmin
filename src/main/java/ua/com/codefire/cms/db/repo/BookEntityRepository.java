package ua.com.codefire.cms.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.BookEntity;

/**
 * Created by User on 27.12.2016.
 */
@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
}
