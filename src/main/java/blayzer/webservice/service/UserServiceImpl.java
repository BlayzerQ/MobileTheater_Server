package blayzer.webservice.service;

import blayzer.webservice.entity.User;
import blayzer.webservice.repository.UserRepository;
import blayzer.webservice.validation.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return new UserDTO(user);
    }

    @Override
    public User getByID(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
