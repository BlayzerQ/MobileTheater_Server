package blayzer.webservice.controllers;

import blayzer.webservice.bussines.objects.User;
import blayzer.webservice.controllers.exceptions.UserNotFoundException;
import blayzer.webservice.presentation.dto.RegistrationForm;
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
    public RegistrationForm getUserById(@PathVariable final long id) {
        User user = userService.getByID(id).orElseThrow(UserNotFoundException::new);
        return new RegistrationForm(user.getLogin(), user.getEmail(), user.getPassword());
    }

    @GetMapping("/getUserByName/{name}")
    public RegistrationForm getUserByName(@PathVariable final String name) {
        User user = userService.getByName(name).orElseThrow(UserNotFoundException::new);
        return new RegistrationForm(user.getLogin(), user.getEmail(), user.getPassword());
    }

}
