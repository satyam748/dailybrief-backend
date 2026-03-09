package com.satyam.dailybrief.client;

import com.satyam.dailybrief.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsDataClient {

    @Value("${newsdata.api.key}")
    private String apiKey;

    @Value("${newsdata.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Article> fetch() {

        String url = apiUrl + "?apikey=" + apiKey + "&country=in&language=en";
    
        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);

        return response.getResults()
                .stream()
                .limit(7)
                .map(r -> new Article(
                        r.getTitle(),
                        r.getDescription(),
                        r.getLink(),
                        r.getSource_id()
                ))
                .collect(Collectors.toList());
    }
}