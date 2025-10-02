package edu.virtualclassroom;

public class Student {
    private final String id;
    private final String name; // optional

    public Student(String id, String name) {
        this.id = id;
        this.name = (name == null || name.isBlank()) ? id : name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}
