package com.codersbay;

import java.util.List;
import java.util.OptionalDouble;

public class LectureEnrollment {

    private Lecture lecture;
    private List<GradedAssignment> assignments;

    public LectureEnrollment(Lecture lecture, List<GradedAssignment> assignments) {
        this.lecture = lecture;
        this.assignments = assignments;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public List<GradedAssignment> getAssignments() {
        return assignments;
    }

    public Integer getGrade() {
        OptionalDouble average = assignments.stream()
                .filter(assignment -> assignment.isGraded())
                .mapToDouble(assignment -> assignment.getGrade())
                .average();
        if (average.isPresent()) {
            return Math.toIntExact(
                    Math.round(average.getAsDouble())
            );
        }
        return 0;
    }

}
