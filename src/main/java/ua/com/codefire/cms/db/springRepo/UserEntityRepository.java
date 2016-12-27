package ua.com.codefire.cms.db.springRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.UserEntity;

/**
 * Created by User on 27.12.2016.
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    @Query("select b from UserEntity b where b.username = :name")
    UserEntity getUserByName(@Param("name") String name);
}