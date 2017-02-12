package com.zavalin.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Statistics> statisticsList = new ArrayList<>();

    @ElementCollection
    private List<Integer> instructorsIds = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getInstructorsIds() {
        return instructorsIds;
    }

    public void setInstructorsIds(List<Integer> instructorsIds) {
        this.instructorsIds = instructorsIds;
    }

    public List<Statistics> getStatisticsList() {
        return statisticsList;
    }

    public void setStatisticsList(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
    }
}
