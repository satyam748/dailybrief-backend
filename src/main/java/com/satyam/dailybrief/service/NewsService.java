package com.satyam.dailybrief.service;

import com.satyam.dailybrief.client.NewsDataClient;
import com.satyam.dailybrief.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsDataClient client;

    public NewsService(NewsDataClient client) {
        this.client = client;
    }

    @Cacheable("news")
    public List<Article> getTopNews(String category) {
        return client.fetch(category);
    }

    @CacheEvict(value = "news", allEntries = true)
    @Scheduled(fixedRate = 12000) // 2 mins
    public void evictCache() {}
}
