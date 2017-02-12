package com.zavalin.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dayOfBirth;

    @ElementCollection
    private List<Integer> coursesIds = new ArrayList<>();

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
