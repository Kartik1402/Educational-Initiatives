package edu.virtualclassroom;

public class ConsoleObserver implements ClassroomObserver {

    public void onClassroomAdded(String name) {
        System.out.println("[Observer] Classroom created: " + name);
    }

    public void onClassroomRemoved(String name) {
        System.out.println("[Observer] Classroom removed: " + name);
    }

    public void onStudentEnrolled(String studentId, String className) {
        System.out.println("[Observer] Student " + studentId + " enrolled in " + className);
    }

    public void onAssignmentScheduled(String className, String assignmentId) {
        System.out.println("[Observer] Assignment scheduled in " + className + " -> " + assignmentId);
    }
    public void onAssignmentSubmitted(String className, String assignmentId, String studentId, String submissionId) {
        System.out.println("[Observer] Submission received: Student " + studentId +
                           ", Class " + className +
                           ", Assignment " + assignmentId +
                           ", Submission " + submissionId);
    }
}
