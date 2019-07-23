package blayzer.webservice.controllers;

import blayzer.webservice.entity.User;
import blayzer.webservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRetrievalController {

    private final UserService userService;

    public UserRetrievalController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserByID/{id}")
    public User getUserById(@PathVariable final long id) {
        User user = userService.getByID(id);
        if(user == null)
            return new User(null, null, null);
        return user;
    }

    @GetMapping("/getUserByName/{name}")
    public User getUserByName(@PathVariable final String name) {
        User user = userService.getByName(name);
        if(user == null)
            return new User(null, null, null);
        return user;
    }

}
