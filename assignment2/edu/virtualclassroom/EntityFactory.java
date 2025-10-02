package edu.virtualclassroom;

import java.time.LocalDateTime;
import java.util.UUID;

public final class EntityFactory {
    private EntityFactory() {}

    public static Student createStudent(String studentId) {
        return new Student(studentId, studentId);
    }

    public static Assignment createAssignment(String title, String description, LocalDateTime due) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        return new Assignment(id, title, description, due);
    }

    public static Submission createSubmission(String studentId, String assignmentId, String content) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        return new Submission(id, studentId, assignmentId, content, LocalDateTime.now());
    }
}
