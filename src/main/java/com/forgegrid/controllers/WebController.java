package com.forgegrid.controllers;

import com.forgegrid.bussines.service.ImagesService;
import com.forgegrid.bussines.service.NewsService;
import com.forgegrid.bussines.service.ProductService;
import com.forgegrid.bussines.service.TaskService;
import com.forgegrid.dal.entity.Image;
import com.forgegrid.dal.entity.NewsEntity;
import com.forgegrid.dal.entity.ProductEntity;
import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.presentation.dto.AccountInfoForm;
import com.forgegrid.presentation.dto.NewsArticle;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final NewsService newsService;
    private final ProductService productService;
    private final TaskService taskService;
    private final ImagesService imagesService;

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

    @GetMapping("/news/images/{id}")
    public void getImage(@PathVariable Long id, HttpServletResponse response) {
        imagesService.findImageById(id).ifPresent(image -> {
            try {
                response.setContentType("image/jpeg");
                IOUtils.write(image.getImage(), response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks";
    }

    @GetMapping("/tasks/task/{id}")
    public String taskItem(@PathVariable final long id, Model model) {
        taskService.getByID(id).ifPresent(task -> model.addAttribute("task", task));
        return "task";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("products", productService.getAll());
        return "catalog";
    }

    @GetMapping("/catalog/item/{id}")
    public String catalogItem(@PathVariable final long id, Model model) {
        Optional<ProductEntity> productEntityOptional = productService.getByID(id);
        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();
            model.addAttribute("product", productEntity);
            return "product";
        }
        return "redirect:/catalog";
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
