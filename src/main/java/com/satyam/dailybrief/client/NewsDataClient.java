package com.satyam.dailybrief.client;

import com.satyam.dailybrief.model.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class NewsDataClient {

    @Value("${newsdata.api.key}")
    private String apiKey;

    @Value("${newsdata.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public NewsDataClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Article> fetch(String category) {

        String url = apiUrl + "?apikey=" + apiKey + "&country=in&language=en";

        if (Strings.isNotBlank(category)) {
            url += "&category=" + category;
        }
    
        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);

        if (Objects.isNull(response) || Objects.isNull(response.getResults())) {
            return List.of();
        }

        return response.getResults()
                .stream()
                .filter(r -> Objects.nonNull(r.getTitle()) && Objects.nonNull(r.getDescription()))
                .limit(30)
                .map(r -> {
                    String description = r.getDescription();
                            String[] words = description.split("\\s+");
                            if (words.length > 50) {
                                description = String.join(" ", Arrays.copyOfRange(words, 0, 50)) + "...";
                            }
                   return Article.builder().title(r.getTitle()).summary(description).url(r.getLink()).source(r.getSource_id()).publishedAt(r.getPubDate()).build();
                })
                .collect(Collectors.toList());
    }
}