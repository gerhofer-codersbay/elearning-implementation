package com.codersbay;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {

    private Long id;
    private String name;
    private List<LectureEnrollment> lectures;

    public Student(Long id, String name, List<LectureEnrollment> lectures) {
        this.id = id;
        this.name = name;
        this.lectures = lectures;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSchoolReport() {
        return lectures.stream()
                .collect(
                        Collectors.toMap(
                                lecture -> lecture.getLecture().getName(),
                                LectureEnrollment::getGrade
                        )
                );
    }

}
