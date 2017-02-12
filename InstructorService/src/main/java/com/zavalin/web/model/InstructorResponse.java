package com.zavalin.web.model;

import com.zavalin.domain.Instructor;

public class InstructorResponse {
    private String firstName;
    private String lastName;
    private String university;

    public InstructorResponse(Instructor instructor) {
        firstName = instructor.getFirstName();
        lastName = instructor.getLastName();
        university = instructor.getUniversity();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUniversity() {
        return university;
    }
}
