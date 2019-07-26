package blayzer.webservice.dal.repository;

import blayzer.webservice.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getUserEntityByLogin(String login);

    boolean existsUserEntityByEmail(String email);
}
