package com.zavalin.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentResponse {
    private String firstName;
    private String lastName;
    private Date dayOfBirth;
    private List<CourseResponse> courses = new ArrayList<>();

    public StudentResponse(StudentResponseWithCourseIds student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();
        dayOfBirth = student.getDayOfBirth();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public List<CourseResponse> getCourses() {
        return courses;
    }
}
