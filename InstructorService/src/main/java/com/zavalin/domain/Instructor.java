package com.zavalin.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String firstName;
    private String lastName;
    private String university;

    @ElementCollection
    private List<Integer> coursesIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public List<Integer> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Integer> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
