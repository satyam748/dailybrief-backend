package com.satyam.dailybrief.model;

public record Article(
        String title,
        String summary,
        String url,
        String source
) {}
