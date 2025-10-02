package edu.virtualclassroom;

import java.time.LocalDateTime;
import java.util.Objects;

public class Submission {
    private final String id;
    private final String studentId;
    private final String assignmentId;
    private final String content;
    private final LocalDateTime timestamp;

    public Submission(String id, String studentId, String assignmentId, String content, LocalDateTime timestamp) {
        this.id = Objects.requireNonNull(id);
        this.studentId = Objects.requireNonNull(studentId);
        this.assignmentId = Objects.requireNonNull(assignmentId);
        this.content = content == null ? "" : content;
        this.timestamp = Objects.requireNonNull(timestamp);
    }

    public String getId() { return id; }
    public String getStudentId() { return studentId; }
    public String getAssignmentId() { return assignmentId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
