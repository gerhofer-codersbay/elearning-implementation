package com.codersbay.domain;

import com.codersbay.Assignment;
import com.codersbay.GradedAssignment;
import com.codersbay.Lecture;
import com.codersbay.LectureEnrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradedAssignmentDAO {

    public List<GradedAssignment> getGradedAssignments(Long studentId, Long lectureId) throws SQLException {
        List<GradedAssignment> gradedAssignments = new ArrayList<>();
        try (Connection connection = openConnection()) {
            PreparedStatement selectLectureEnrollments = connection.prepareStatement(
                    "SELECT \n" +
                            "    gradedAssignment.id,\n" +
                            "    gradedAssignment.points_scored,\n" +
                            "    gradedAssignment.grade,\n" +
                            "    gradedAssignment.assignment_id,\n" +
                            "    assignment.description,\n" +
                            "    assignment.max_points\n" +
                            "FROM\n" +
                            "    elearning.graded_assignment gradedAssignment\n" +
                            "        INNER JOIN\n" +
                            "    elearning.`assignment` assignment ON gradedAssignment.assignment_id = assignment.id\n" +
                            "WHERE\n" +
                            "    gradedAssignment.lecture_participation_lecture_id = ?\n" +
                            "        AND gradedAssignment.lecture_participation_student_id = ?");
            selectLectureEnrollments.setLong(1, lectureId);
            selectLectureEnrollments.setLong(2, studentId);
            ResultSet resultSet = selectLectureEnrollments.executeQuery();

            while(resultSet.next()) {
                Integer pointsScored = resultSet.getInt("gradedAssignment.points_scored");
                Integer grade = resultSet.getInt("gradedAssignment.grade");
                Integer maxPoints = resultSet.getInt("assignment.max_points");
                String description = resultSet.getString("assignment.description");
                Assignment assignment = new Assignment(description, maxPoints);
                GradedAssignment gradedAssignment = new GradedAssignment(assignment, pointsScored, grade);
                gradedAssignments.add(gradedAssignment);
            }
        }
        return gradedAssignments;
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
