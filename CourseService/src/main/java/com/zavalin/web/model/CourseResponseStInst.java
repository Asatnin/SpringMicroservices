package com.zavalin.web.model;

import com.zavalin.domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseResponseStInst {
    private String title;
    private List<StatisticsResponse> statistics = new ArrayList<>();
    private List<Integer> instructors = new ArrayList<>();

    public CourseResponseStInst(Course course) {
        title = course.getTitle();
        statistics = course.getStatisticsList()
                .stream()
                .map(StatisticsResponse::new)
                .collect(Collectors.toList());
        instructors = course.getInstructorsIds();
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
