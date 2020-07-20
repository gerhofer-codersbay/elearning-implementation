package com.codersbay;

public class GradedAssignment {

    private Assignment assignment;
    private Integer pointsScored;
    private Integer grade;

    public GradedAssignment(Assignment assignment, Integer pointsScored, Integer grade) {
        this.assignment = assignment;
        this.pointsScored = pointsScored;
        this.grade = grade;
    }

    public boolean isGraded() {
        return grade != null;
    }

    public Integer getGrade() {
        return grade;
    }
}
