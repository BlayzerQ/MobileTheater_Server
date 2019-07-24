package blayzer.webservice.controllers;

import blayzer.webservice.entity.User;
import blayzer.webservice.service.NewsService;
import blayzer.webservice.service.ProductService;
import blayzer.webservice.service.TaskService;
import blayzer.webservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final UserService userService;
    private final NewsService newsService;
    private final ProductService productService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    public WebController(UserService userService, NewsService newsService, ProductService productService, TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.newsService = newsService;
        this.productService = productService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("message", "RESTful API is working.");
        return "index";
    }

    @GetMapping("/account")
    public String account(Model model, HttpServletRequest request, Principal principal) {
        model.addAttribute("user", userService.getByName(principal.getName()));
        return "account";
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("news", newsService.getAll());
        return "news";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks";
    }

    @GetMapping("/tasks/task/{id}")
    public String taskItem(@PathVariable final long id, Model model) {
        model.addAttribute("task", taskService.getByID(id));
        return "task";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("products", productService.getAll());
        return "catalog";
    }

    @GetMapping("/catalog/item/{id}")
    public String catalogItem(@PathVariable final long id, Model model) {
        model.addAttribute("product", productService.getByID(id));
        return "product";
    }

    @GetMapping("/payment/{id}")
    public String payment(@PathVariable final long id, Model model) {
        return "product";
    }

    @GetMapping("/convert")
    public Model convert(Model model) {
        return model;
    }

    @GetMapping("/registration")
    public @ResponseBody
    ModelAndView registration(@ModelAttribute(value = "message") String message, ModelAndView model) {
        model.addObject("message", message);
        model.addObject("user", new User());
        return model;
    }

    @PostMapping("/registration")
    public String submit(@Valid @ModelAttribute("employee") User userform, BindingResult result, HttpServletRequest request, ModelAndView model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "error";
        }

        String login = userform.getLogin();
        String email = userform.getEmail();
        String password = userform.getPassword();

        //Получение данные из формы и добавление пользователя в базу данных
        User user = new User(login, email, passwordEncoder.encode(password));
        if (userService.getByName(login) != null) {
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

    @GetMapping("/account/edit")
    public String accountedit(Model model) {
        model.addAttribute("user", new User());
        return "/accountedit";
    }

    @PostMapping("/account/edit")
    public String accountedit(@Valid @ModelAttribute("user") User userform, HttpServletRequest request, Principal principal) {
        User loggeduser = userService.getByName(principal.getName());

        //Получение данные из формы и обновление пользователя в базе данных
        String login = userform.getLogin();
        String email = userform.getEmail();
        String password = userform.getPassword();

        if(!login.equals(""))
            loggeduser.setLogin(login);
        if(!email.equals(""))
            loggeduser.setEmail(email);
        if(!password.equals(""))
            loggeduser.setPassword(passwordEncoder.encode(password));

        userService.editUser(loggeduser);

        return "redirect:/account";
    }

    @PostMapping("/convertPost")
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
