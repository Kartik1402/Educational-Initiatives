package edu.virtualclassroom;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment {
    private final String id;
    private final String title;
    private final String description;
    private final LocalDateTime due;

    public Assignment(String id, String title, String description, LocalDateTime due) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.description = description == null ? "" : description;
        this.due = Objects.requireNonNull(due);
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDue() { return due; }
}
