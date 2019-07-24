package blayzer.webservice.service;

import blayzer.webservice.entity.User;
import blayzer.webservice.validation.UserDTO;

import java.util.List;

public interface UserService {

    User getByName(String username);
    UserDTO getByEmail(String email);
    User getByID(Long id);
    User addUser(User user);
    User editUser(User user);
    void delete(Long id);
    List<User> getAll();
}
