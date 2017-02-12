package com.zavalin.web.model;

public class StatisticsResponse {
    private String year;
    private double averageScore;

    public void setYear(String year) {
        this.year = year;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getYear() {
        return year;
    }

    public double getAverageScore() {
        return averageScore;
    }
}
