package com.zavalin.web.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseResponseStInst {
    private String title;
    private List<StatisticsResponse> statistics = new ArrayList<>();
    private List<Integer> instructors = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatistics(List<StatisticsResponse> statistics) {
        this.statistics = statistics;
    }

    public void setInstructors(List<Integer> instructors) {
        this.instructors = instructors;
    }

    public String getTitle() {
        return title;
    }

    public List<StatisticsResponse> getStatistics() {
        return statistics;
    }

    public List<Integer> getInstructors() {
        return instructors;
    }
}
