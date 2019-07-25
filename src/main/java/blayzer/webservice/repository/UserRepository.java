package blayzer.webservice.repository;

import blayzer.webservice.dal.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select b from UserEntity b where b.login = :name")
    UserEntity findByName(@Param("name") String name);

    @Query("select usr from UserEntity usr where usr.email = :email")
    UserEntity findByEmail(@Param("email") String email);
}
