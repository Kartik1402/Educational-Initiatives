package edu.virtualclassroom;

import java.util.*;

public class Classroom {
    private final String name;
    private final Map<String, Student> students = new LinkedHashMap<>();
    private final Map<String, Assignment> assignments = new LinkedHashMap<>();
    private final Map<String, List<Submission>> submissions = new HashMap<>();

    public Classroom(String name) {
        this.name = Objects.requireNonNull(name, "Class name cannot be null");
    }

    public String getName() { return name; }

    public synchronized void addStudent(Student s) throws EnrollmentException {
        if (students.containsKey(s.getId())) {
            throw new EnrollmentException("Student " + s.getId() + " already enrolled in " + name);
        }
        students.put(s.getId(), s);
    }

    public synchronized List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    public synchronized void addAssignment(Assignment a) {
        assignments.put(a.getId(), a);
        submissions.put(a.getId(), new ArrayList<>());
    }

    public synchronized List<Assignment> listAssignments() {
        return new ArrayList<>(assignments.values());
    }

    public synchronized Assignment getAssignment(String assignmentId) {
        return assignments.get(assignmentId);
    }

    public synchronized boolean hasStudent(String studentId) {
        return students.containsKey(studentId);
    }

    public synchronized void addSubmission(String assignmentId, Submission sub) throws SubmissionException {
        if (!assignments.containsKey(assignmentId)) {
            throw new SubmissionException("Assignment not found: " + assignmentId);
        }
        if (!students.containsKey(sub.getStudentId())) {
            throw new SubmissionException("Student not enrolled in class: " + sub.getStudentId());
        }
        submissions.get(assignmentId).add(sub);
    }

    public synchronized List<Submission> getSubmissions(String assignmentId) {
        return submissions.getOrDefault(assignmentId, Collections.emptyList());
    }
}
