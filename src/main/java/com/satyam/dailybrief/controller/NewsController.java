package com.satyam.dailybrief.controller;

import com.satyam.dailybrief.model.Article;
import com.satyam.dailybrief.model.NewsPage;
import com.satyam.dailybrief.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public NewsPage getNews(@RequestParam(required = false) String category, @RequestParam(required = false) String pageToken) {
        return newsService.getTopNews(category, pageToken);
    }

    @GetMapping("/search")
    public List<Article> searchNews(@RequestParam String q) {
        return newsService.searchNews(q);
    }
}
