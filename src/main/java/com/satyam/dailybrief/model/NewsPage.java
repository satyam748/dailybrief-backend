package com.satyam.dailybrief.model;

import lombok.Builder;
import java.util.List;

@Builder
public record NewsPage (
    List<Article> articles,
    String nextPage
) {}
