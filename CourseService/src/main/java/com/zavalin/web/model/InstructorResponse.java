package com.zavalin.web.model;


public class InstructorResponse {
    private String firstName;
    private String lastName;
    private String university;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUniversity() {
        return university;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
