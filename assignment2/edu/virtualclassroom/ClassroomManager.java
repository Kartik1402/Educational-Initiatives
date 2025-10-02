package edu.virtualclassroom;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class ClassroomManager {
    private static ClassroomManager instance;
    private final Map<String, Classroom> classrooms = new HashMap<>();
    private final Map<String, Student> students = new HashMap<>();
    private final Logger logger = LoggerUtil.getLogger();
    private final List<ClassroomObserver> observers = new ArrayList<>();

    private ClassroomManager() {
        logger.info("ClassroomManager initialized.");
    }

    public static synchronized ClassroomManager getInstance() {
        if (instance == null) instance = new ClassroomManager();
        return instance;
    }

    // Observer
    public synchronized void registerObserver(ClassroomObserver o) { observers.add(o); }
    public synchronized void unregisterObserver(ClassroomObserver o) { observers.remove(o); }

    // Classrooms
    public synchronized void addClassroom(String name) throws EntityAlreadyExistsException {
        if (classrooms.containsKey(name)) throw new EntityAlreadyExistsException("Classroom already exists: " + name);
        Classroom c = new Classroom(name);
        classrooms.put(name, c);
        logger.info("Classroom added: " + name);
        notifyClassroomAdded(name);
    }

    public synchronized void removeClassroom(String name) throws EntityNotFoundException {
        if (!classrooms.containsKey(name)) throw new EntityNotFoundException("Classroom not found: " + name);
        classrooms.remove(name);
        logger.info("Classroom removed: " + name);
        notifyClassroomRemoved(name);
    }

    public synchronized List<String> listClassrooms() {
        return new ArrayList<>(classrooms.keySet());
    }

    // Students
    public synchronized void enrollStudent(String studentId, String className)
            throws EntityNotFoundException, EnrollmentException {
        Classroom classroom = getClassroomOrThrow(className);
        Student s = students.get(studentId);
        if (s == null) {
            s = EntityFactory.createStudent(studentId);
            students.put(studentId, s);
            logger.info("Created new Student record: " + studentId);
        }
        classroom.addStudent(s);
        logger.info(String.format("Student %s enrolled in %s", studentId, className));
        notifyStudentEnrolled(studentId, className);
    }

    public synchronized List<Student> listStudentsInClass(String className) throws EntityNotFoundException {
        Classroom c = getClassroomOrThrow(className);
        return c.listStudents();
    }

    // Assignments
    public synchronized String scheduleAssignment(String className, String title, String description, LocalDateTime due)
            throws EntityNotFoundException {
        Classroom c = getClassroomOrThrow(className);
        Assignment a = EntityFactory.createAssignment(title, description, due);
        c.addAssignment(a);
        logger.info(String.format("Assignment scheduled: %s for class %s (id=%s)", title, className, a.getId()));
        notifyAssignmentScheduled(className, a.getId());
        return a.getId();
    }

    public synchronized List<Assignment> listAssignments(String className) throws EntityNotFoundException {
        Classroom c = getClassroomOrThrow(className);
        return c.listAssignments();
    }

    // Submissions with transient retry policy
    public void submitAssignment(String studentId, String className, String assignmentId, String content)
            throws EntityNotFoundException, SubmissionException {
        Classroom c = getClassroomOrThrow(className);
        // Basic checks
        if (!students.containsKey(studentId)) {
            throw new EntityNotFoundException("Student not found: " + studentId);
        }
        if (!c.hasStudent(studentId)) {
            throw new SubmissionException("Student " + studentId + " is not enrolled in class " + className);
        }
        if (c.getAssignment(assignmentId) == null) {
            throw new EntityNotFoundException("Assignment not found: " + assignmentId);
        }

        Submission sub = EntityFactory.createSubmission(studentId, assignmentId, content);

        // Retry on TransientException - up to 3 attempts
        int maxAttempts = 3;
        int attempt = 0;
        while (true) {
            attempt++;
            try {
                // process submission (may throw SubmissionException or TransientException)
                processSubmission(c, assignmentId, sub);
                logger.info(String.format("Submission accepted: %s (student=%s, assignment=%s)", sub.getId(), studentId, assignmentId));
                notifyAssignmentSubmitted(className, assignmentId, studentId, sub.getId());
                return;
            } catch (TransientException te) {
                logger.warning("Transient error while submitting (attempt " + attempt + "): " + te.getMessage());
                if (attempt >= maxAttempts) {
                    throw new SubmissionException("Submission failed after " + attempt + " attempts: " + te.getMessage());
                }
                // exponential backoff - short sleep
                try {
                    Thread.sleep(200L * attempt);
                } catch (InterruptedException ignore) {
                    Thread.currentThread().interrupt();
                    throw new SubmissionException("Submission interrupted during retry.");
                }
            }
        }
    }

    private void processSubmission(Classroom c, String assignmentId, Submission sub) throws SubmissionException, TransientException {
        // In real system, this may call external services (storage, virus-scan, etc.)
        // For demo: we simulate no transient error unless the submission content includes the magic token.
        if (sub.getContent().contains("__TRANSIENT_FAIL__")) {
            // simulate a transient failure (e.g., network storage issue)
            throw new TransientException("Simulated transient failure for demo.");
        }
        // add to classroom submissions
        c.addSubmission(assignmentId, sub);
    }

    // counts
    public synchronized int getNumberOfClassrooms() { return classrooms.size(); }
    public synchronized int getNumberOfStudents() { return students.size(); }

    public synchronized int getNumberOfAssignments() {
        int total = 0;
        for (Classroom c : classrooms.values()) total += c.listAssignments().size();
        return total;
    }

    // helpers
    private synchronized Classroom getClassroomOrThrow(String className) throws EntityNotFoundException {
        Classroom c = classrooms.get(className);
        if (c == null) throw new EntityNotFoundException("Classroom not found: " + className);
        return c;
    }

    // Observer notifications
    private void notifyClassroomAdded(String name) { observers.forEach(o -> o.onClassroomAdded(name)); }
    private void notifyClassroomRemoved(String name) { observers.forEach(o -> o.onClassroomRemoved(name)); }
    private void notifyStudentEnrolled(String studentId, String className) { observers.forEach(o -> o.onStudentEnrolled(studentId, className)); }
    private void notifyAssignmentScheduled(String className, String assignmentId) { observers.forEach(o -> o.onAssignmentScheduled(className, assignmentId)); }
    private void notifyAssignmentSubmitted(String className, String assignmentId, String studentId, String submissionId) {
        observers.forEach(o -> o.onAssignmentSubmitted(className, assignmentId, studentId, submissionId));
    }
}
