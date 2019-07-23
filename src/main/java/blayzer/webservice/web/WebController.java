package blayzer.webservice.web;

import blayzer.webservice.entity.User;
import blayzer.webservice.service.NewsService;
import blayzer.webservice.service.ProductService;
import blayzer.webservice.service.TaskService;
import blayzer.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;
    @Autowired
    ProductService productService;
    @Autowired
    TaskService taskService;
    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("message", "RESTful API is working.");
        return "index";
    }

    @GetMapping(value = "/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping(value = "/account/purchases")
    public String purchases(Model model) {
        return "purchases";
    }

    @GetMapping(value = "/account/balance")
    public String balance(Model model) {
        return "balance";
    }

    @GetMapping(value = "/account")
    public String account(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null)
            model.addAttribute("user", userService.getByName(principal.getName()));
        return "account";
    }

    @GetMapping(value = "/news")
    public String news(Model model) {
        model.addAttribute("news", newsService.getAll());
        return "news";
    }

    @GetMapping(value = "/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks";
    }

    @GetMapping(value = "/tasks/task/{id}")
    public String taskItem(@PathVariable final long id, Model model) {
        model.addAttribute("task", taskService.getByID(id));
        return "task";
    }

    @GetMapping(value = "/catalog")
    public String catalog(Model model) {
        model.addAttribute("products", productService.getAll());
        return "catalog";
    }

    @GetMapping(value = "/catalog/item/{id}")
    public String catalogItem(@PathVariable final long id, Model model) {
        model.addAttribute("product", productService.getByID(id));
        return "product";
    }

    @GetMapping(value = "/payment/{id}")
    public String payment(@PathVariable final long id, Model model) {
        return "product";
    }

    @GetMapping(value = "/convert")
    public Model convert(Model model) {
        return model;
    }

    @GetMapping(value = "/registration")
    public @ResponseBody ModelAndView registration(@ModelAttribute(value="message") String message, ModelAndView model) {
        model.addObject("message", message);
        model.addObject("user", new User());
        return model;
        //return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public String submit(@Valid @ModelAttribute("employee") User userform, BindingResult result, HttpServletRequest request, ModelAndView model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "error";
        }

        String login = userform.getLogin();
        String email = userform.getEmail();
        String password = userform.getPassword();

        //Получение данные из формы и добавление пользователя в базу данных
        User user = new User(login, email, shaPasswordEncoder.encodePassword(password, "")); //соль отсутствует, возможно стоит добавить
        if(userService.getByName(login) != null) {
            redirectAttributes.addAttribute("message", "Логин уже используется");
            return "redirect:/registration";
        }
        userService.addUser(user);

        //Автоматический вход после регистрации
        try {
            request.login(login, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/index";
    }

    @GetMapping(value = "/resetpassword")
    public String resetpassword(Model model) {
        return "resetpassword";
    }

    @PostMapping(value = "/convertPost")
    @ResponseBody
    public String convertPost(Model model) {
        return "Empty Data";
    }

//    private void redirect(ModelAndView modelAndView){
//        //Перенаправление на GET метод
//        RedirectView redirectView = new RedirectView("registration");
//        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//        modelAndView.setView(redirectView);
//    }
}
