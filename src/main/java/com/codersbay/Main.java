package com.codersbay;

import com.codersbay.domain.StudentDAO;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException {

        StudentDAO studentDAO = new StudentDAO();
        List<Student> allStudents = studentDAO.getStudents();
        for (Student student : allStudents) {
            System.out.println("#####################");
            System.out.println(student.getName());
            Map<String, Integer> schoolReport = student.getSchoolReport();
            for (Map.Entry<String, Integer> entry : schoolReport.entrySet()) {
                if (entry.getValue() != 0) {
                    System.out.printf("%-60s%d\n", entry.getKey(), entry.getValue());
                } else {
                    System.out.printf("%-60s\n", entry.getKey());
                }
            }
            System.out.println("#####################");
            System.out.println();
        }
    }

}
