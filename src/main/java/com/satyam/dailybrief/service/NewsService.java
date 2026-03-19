package com.satyam.dailybrief.service;

import com.satyam.dailybrief.client.NewsDataClient;
import com.satyam.dailybrief.model.Article;
import com.satyam.dailybrief.model.NewsPage;
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

    @Cacheable(value = "news", key = "#category != null ? #category : 'top'", condition = "#pageToken == null")
    public NewsPage getTopNews(String category, String pageToken) {
        System.out.println("Fetching from NewsData API for category: " + category);
        return client.fetch(category, pageToken);
    }

    @CacheEvict(value = "news", allEntries = true)
    @Scheduled(fixedRate = 900_000) // 15 minutes
    public void evictCache() {
        System.out.println("Cache cleared");
    }

    public List<Article> searchNews(String query) {
        return client.search(query);
    }
}
