package blayzer.webservice.service;

import blayzer.webservice.bussines.objects.User;
import blayzer.webservice.dal.dao.entity.UserEntity;
import blayzer.webservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getByName(String name) {
        return Optional.ofNullable(entityToUser(userRepository.findByName(name)));
    }

    @Override
    public Optional<User> getByEmail(@Nullable String email) {
        if (email == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(entityToUser(userRepository.findByEmail(email)));
    }

    @Override
    public Optional<User> getByID(Long id) {
        return Optional.ofNullable(entityToUser(userRepository.getOne(id)));
    }

    @Override
    public void persist(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setLogin(user.getLogin());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setMoney(user.getMoney());
        userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void persist(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream().map(this::entityToUser).collect(Collectors.toList());
    }

    private @Nullable
    User entityToUser(@Nullable UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setLogin(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setMoney(userEntity.getMoney());
        user.setRole(userEntity.getRole());
        return user;
    }

}
