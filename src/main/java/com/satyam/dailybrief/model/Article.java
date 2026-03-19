package com.satyam.dailybrief.model;

import lombok.Builder;

@Builder
public record Article(
        String title,
        String summary,
        String url,
        String source
) {}
