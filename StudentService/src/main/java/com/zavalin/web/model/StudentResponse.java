package com.zavalin.web.model;

import com.zavalin.domain.Student;

import java.util.Date;

public class StudentResponse {
    private String firstName;
    private String lastName;
    private Date dayOfBirth;

    public StudentResponse(Student student) {
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

    public Date getDayOfBirth() {
        return dayOfBirth;
    }
}
