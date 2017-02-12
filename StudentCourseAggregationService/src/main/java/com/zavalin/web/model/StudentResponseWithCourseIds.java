package com.zavalin.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentResponseWithCourseIds {
    private String firstName;
    private String lastName;
    private Date dayOfBirth;
    private List<Integer> coursesIds = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public List<Integer> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Integer> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
