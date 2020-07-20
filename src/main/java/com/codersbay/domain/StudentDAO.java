package com.codersbay.domain;

import com.codersbay.LectureEnrollment;
import com.codersbay.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private LectureDAO lectureDAO = new LectureDAO();

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = openConnection()) {
            PreparedStatement selectStudents = connection.prepareStatement(
                    "select id, name from student");
            ResultSet resultSet = selectStudents.executeQuery();

            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                List<LectureEnrollment> lectureEnrollments = lectureDAO.getLectureEnrollments(id);
                Student student = new Student(id, name, lectureEnrollments);
                students.add(student);
            }
        }
        return students;
    }

    private Connection openConnection() throws SQLException {
        String schema = "elearning";
        String username = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/"
                + schema + "?user="
                + username + "&password=" + password;

        return DriverManager.getConnection(connectionUrl);
    }

}
