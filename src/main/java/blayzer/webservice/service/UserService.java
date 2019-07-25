package blayzer.webservice.service;

import blayzer.webservice.bussines.objects.User;
import blayzer.webservice.dal.dao.entity.UserEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByName(String username);

    Optional<User> getByEmail(@Nullable String email);

    Optional<User> getByID(Long id);

    void persist(User user);

    void persist(UserEntity user);

    void delete(Long id);

    List<User> getAll();
}
