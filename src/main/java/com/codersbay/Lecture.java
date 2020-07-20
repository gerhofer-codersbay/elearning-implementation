package com.codersbay;

import java.util.List;

public class Lecture {

    private Long id;
    private String name;
    private String teacher;
    private List<Assignment> assignments;

    public Lecture(Long id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}
