package com.zavalin.web.model;

import java.util.ArrayList;
import java.util.List;

public class CourseResponse {
    private String title;
    private List<StatisticsResponse> statistics = new ArrayList<>();
    private List<InstructorResponse> instructors = new ArrayList<>();

    public CourseResponse(CourseResponseStInst course) {
        title = course.getTitle();
        statistics = course.getStatistics();
    }

    public String getTitle() {
        return title;
    }

    public List<StatisticsResponse> getStatistics() {
        return statistics;
    }

    public List<InstructorResponse> getInstructors() {
        return instructors;
    }
}
