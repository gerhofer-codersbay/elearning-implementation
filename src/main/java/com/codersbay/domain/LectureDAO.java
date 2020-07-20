package com.codersbay.domain;

import com.codersbay.Lecture;
import com.codersbay.LectureEnrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO {

    private GradedAssignmentDAO gradedAssignmentDAO = new GradedAssignmentDAO();

    public List<LectureEnrollment> getLectureEnrollments(Long studentId) throws SQLException {
        List<LectureEnrollment> lectureEnrollments = new ArrayList<>();
        try (Connection connection = openConnection()) {
            PreparedStatement selectLectureEnrollments = connection.prepareStatement(
                    "select id, name, teacher from lecture where id in (select lecture_id from lecture_participation where student_id = ?)");
            selectLectureEnrollments.setLong(1, studentId);
            ResultSet resultSet = selectLectureEnrollments.executeQuery();

            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String teacher = resultSet.getString("teacher");
                Lecture lecture = new Lecture(id, name, teacher);
                LectureEnrollment lectureEnrollment = new LectureEnrollment(lecture, gradedAssignmentDAO.getGradedAssignments(studentId, id));
                lectureEnrollments.add(lectureEnrollment);
            }
        }
        return lectureEnrollments;
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
