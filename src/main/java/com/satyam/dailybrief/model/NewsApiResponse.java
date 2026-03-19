package com.satyam.dailybrief.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsApiResponse {
    private List<Result> results;
    private String nextPage;
}
