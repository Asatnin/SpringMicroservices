package com.zavalin.web.model;

import com.zavalin.domain.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentResponseWithCourseIds {
    private String firstName;
    private String lastName;
    private Date dayOfBirth;
    private List<Integer> coursesIds = new ArrayList<>();

    public StudentResponseWithCourseIds(Student student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();
        dayOfBirth = student.getDayOfBirth();
        coursesIds = student.getCoursesIds();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public List<Integer> getCoursesIds() {
        return coursesIds;
    }
}
