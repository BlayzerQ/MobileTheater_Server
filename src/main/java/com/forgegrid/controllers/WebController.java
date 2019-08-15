package com.forgegrid.controllers;

import com.forgegrid.dal.entity.NewsEntity;
import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.bussines.service.NewsService;
import com.forgegrid.bussines.service.ProductService;
import com.forgegrid.bussines.service.TaskService;
import com.forgegrid.presentation.dto.AccountInfoForm;
import com.forgegrid.presentation.dto.NewsArticle;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class WebController {

    private final NewsService newsService;
    private final ProductService productService;
    private final TaskService taskService;

    public WebController(NewsService newsService, ProductService productService, TaskService taskService) {
        this.newsService = newsService;
        this.productService = productService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "RESTful API is working.");
        return "index";
    }

    @GetMapping("/account")
    public String account(Model model, HttpServletRequest request, @AuthenticationPrincipal UserEntity user) {
        AccountInfoForm accountInfoForm = new AccountInfoForm(
                user.getLogin(),
                user.getEmail(),
                user.getRole(),
                user.getMoney()
        );
        model.addAttribute("user", accountInfoForm);
        return "account";
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("news", newsService.getAll().stream().map(NewsArticle::new).collect(Collectors.toList()));
        return "news";
    }

    @GetMapping("/news/{id}")
    public String news(Model model, @PathVariable long id) {
        Optional<NewsEntity> newsEntity = newsService.getByID(id);
        if (!newsEntity.isPresent()) {
            return "redirect:/news";
        }
        model.addAttribute("article", new NewsArticle(newsEntity.get()));
        return "news_article";
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
