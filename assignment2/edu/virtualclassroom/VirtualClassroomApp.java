package edu.virtualclassroom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class VirtualClassroomApp {
    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        ClassroomManager manager = ClassroomManager.getInstance();
        manager.registerObserver(new ConsoleObserver());
        Scanner scanner = new Scanner(System.in);

        printWelcome();

        while (true) {
            System.out.print("\n> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] tokens = line.split("\\s+");

            String cmd = tokens[0].toLowerCase();

            try {
                switch (cmd) {
                    case "add_classroom":
                        if (tokens.length < 2) {
                            System.out.println("Usage: add classroom <ClassName>");
                        } else {
                            String className = tokens[1];
                            manager.addClassroom(className);
                            System.out.printf("Classroom [%s] has been created.%n", className);
                        }
                        break;

                    case "remove_classroom":
                        if (tokens.length < 2) {
                            System.out.println("Usage: remove classroom <ClassName>");
                        } else {
                            String className = tokens[1];
                            manager.removeClassroom(className);
                            System.out.printf("Classroom [%s] removed.%n", className);
                        }
                        break;

                    case "list_classrooms":
                        List<String> classes = manager.listClassrooms();
                        if (classes.isEmpty()) {
                            System.out.println("No classrooms exist.");
                        } else {
                            System.out.println("Classrooms:");
                            classes.forEach(c -> System.out.println(" - " + c));
                        }
                        break;

                    case "add_student":
                        if (tokens.length < 3) {
                            System.out.println("Usage: add student <StudentID> <ClassName>");
                        } else {
                            String studentId = tokens[1];
                            String className = tokens[2];
                            manager.enrollStudent(studentId, className);
                            System.out.printf("Student [%s] has been enrolled in [%s].%n", studentId, className);
                        }
                        break;

                    case "list_students":
                        if (tokens.length < 2) {
                            System.out.println("Usage: list students <ClassName>");
                        } else {
                            String className = tokens[1];
                            List<Student> sList = manager.listStudentsInClass(className);
                            if (sList.isEmpty()) {
                                System.out.printf("No students enrolled in [%s].%n", className);
                            } else {
                                System.out.printf("Students in [%s]:%n", className);
                                sList.forEach(s -> System.out.println(" - " + s.getId()));
                            }
                        }
                        break;

                    case "schedule_assignment":
                        if (tokens.length < 2) {
                            System.out.println("Usage: schedule assignment <ClassName>");
                        } else {
                            String className = tokens[1];
                            System.out.print("Title: ");
                            String title = scanner.nextLine().trim();
                            System.out.print("Description: ");
                            String desc = scanner.nextLine().trim();
                            System.out.print("Due (yyyy-MM-dd HH:mm): ");
                            String dueStr = scanner.nextLine().trim();
                            LocalDateTime due = null;
                            try {
                                due = LocalDateTime.parse(dueStr, DT_FMT);
                            } catch (DateTimeParseException ex) {
                                System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm");
                                break;
                            }
                            String assignmentId = manager.scheduleAssignment(className, title, desc, due);
                            System.out.printf("Assignment for [%s] has been scheduled. id=%s%n", className, assignmentId);
                        }
                        break;

                    case "list_assignments":
                        if (tokens.length < 2) {
                            System.out.println("Usage: list assignments <ClassName>");
                        } else {
                            String className = tokens[1];
                            List<Assignment> assignments = manager.listAssignments(className);
                            if (assignments.isEmpty()) {
                                System.out.printf("No assignments scheduled for [%s].%n", className);
                            } else {
                                System.out.printf("Assignments for [%s]:%n", className);
                                assignments.forEach(a -> System.out.printf(" - %s | due: %s | id: %s%n",
                                        a.getTitle(), a.getDue().format(DT_FMT), a.getId()));
                            }
                        }
                        break;

                    case "submit_assignment":
                        if (tokens.length < 4) {
                            System.out.println("Usage: submit assignment <StudentID> <ClassName> <AssignmentId>");
                        } else {
                            String studentId = tokens[1];
                            String className = tokens[2];
                            String assignmentId = tokens[3];
                            System.out.print("Submission content (single line): ");
                            String content = scanner.nextLine().trim();
                            manager.submitAssignment(studentId, className, assignmentId, content);
                            System.out.printf("Assignment submitted by Student [%s] in [%s].%n", studentId, className);
                        }
                        break;

                    case "stats":
                        System.out.printf("Number of Classrooms: %d%n", manager.getNumberOfClassrooms());
                        System.out.printf("Number of Students: %d%n", manager.getNumberOfStudents());
                        System.out.printf("Number of Assignments: %d%n", manager.getNumberOfAssignments());
                        break;

                    case "help":
                        printHelp();
                        break;

                    case "exit":
                        System.out.println("Bye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Unknown command. Type 'help' for list of commands.");
                }
            } catch (EntityAlreadyExistsException | EntityNotFoundException |
                     EnrollmentException | SubmissionException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void printWelcome() {
        System.out.println("Virtual Classroom Manager - console");
        System.out.println("Type 'help' for commands");
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add classroom <ClassName>");
        System.out.println("  remove classroom <ClassName>");
        System.out.println("  list classrooms");
        System.out.println("  add student <StudentID> <ClassName>");
        System.out.println("  list students <ClassName>");
        System.out.println("  schedule assignment <ClassName>    (then enter title/desc/due)");
        System.out.println("  list assignments <ClassName>");
        System.out.println("  submit assignment <StudentID> <ClassName> <AssignmentId>  (then enter submission)");
        System.out.println("  stats");
        System.out.println("  help");
        System.out.println("  exit");
    }
}
