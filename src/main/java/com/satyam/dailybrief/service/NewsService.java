package com.satyam.dailybrief.service;

import com.satyam.dailybrief.client.NewsDataClient;
import com.satyam.dailybrief.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsDataClient client;

    public NewsService(NewsDataClient client) {
        this.client = client;
    }

    public List<Article> getTopNews() {
        return client.fetch();
    }
}
