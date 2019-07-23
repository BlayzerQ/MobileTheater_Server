package blayzer.webservice.service;

import blayzer.webservice.entities.User;

import java.util.List;

public interface UserService {

    User getByName(String username);
    User getByID(Long id);
    User addUser(User user);
    User editUser(User user);
    void delete(Long id);
    List<User> getAll();
}
