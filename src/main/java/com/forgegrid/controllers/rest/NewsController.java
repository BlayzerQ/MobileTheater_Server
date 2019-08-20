package com.forgegrid.controllers.rest;

import com.forgegrid.bussines.service.NewsService;
import com.forgegrid.presentation.dto.NewsArticle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/news")
@CrossOrigin("http://localhost:3000")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsArticle> getAllNewsArticles() {
        return newsService.getAll().stream().map(NewsArticle::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NewsArticle getNewsArticleById(@PathVariable long id) {
        return newsService.getByID(id).map(NewsArticle::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News article not found"));
    }
}
