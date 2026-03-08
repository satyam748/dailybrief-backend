package com.satyam.dailybrief.model;

public record Article(
        String title,
        String description,
        String link,
        String article_id
) {}
