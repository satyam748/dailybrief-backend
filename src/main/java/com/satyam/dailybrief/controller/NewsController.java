package com.satyam.dailybrief.controller;

import com.satyam.dailybrief.model.Article;
import com.satyam.dailybrief.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "https://dailybrief-ui.netlify.app")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<Article> getNews(@RequestParam(required = false) String category) {
        return newsService.getTopNews(category);
    }

    @GetMapping("/search")
    public List<Article> searchNews(@RequestParam String q) {
        return newsService.searchNews(q);
    }
}
