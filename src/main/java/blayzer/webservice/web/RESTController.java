package blayzer.webservice.web;

import blayzer.webservice.entities.User;
import blayzer.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class RESTController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/getUserByID/{id}"}, method = RequestMethod.GET)
    public @ResponseBody User getUserById(@PathVariable final long id) {
        User user = userService.getByID(id);
        if(user == null)
            return new User(null, null, null);
        return user;
    }

    @RequestMapping(value = {"/getUserByName/{name}"}, method = RequestMethod.GET)
    public @ResponseBody User getUserByName(@PathVariable final String name) {
        User user = userService.getByName(name);
        if(user == null)
            return new User(null, null, null);
        return user;
    }

}
