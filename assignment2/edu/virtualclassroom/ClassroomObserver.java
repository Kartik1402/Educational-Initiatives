package edu.virtualclassroom;

public interface ClassroomObserver {
    void onClassroomAdded(String name);
    void onClassroomRemoved(String name);
    void onStudentEnrolled(String studentId, String className);
    void onAssignmentScheduled(String className, String assignmentId);
    void onAssignmentSubmitted(String className, String assignmentId, String studentId, String submissionId);
}
