package com.zavalin.web.model;

import com.zavalin.domain.Statistics;

public class StatisticsResponse {
    private String year;
    private double averageScore;

    public StatisticsResponse(Statistics statistics) {
        year = statistics.getYear();
        averageScore = statistics.getAverageScore();
    }

    public String getYear() {
        return year;
    }

    public double getAverageScore() {
        return averageScore;
    }
}
