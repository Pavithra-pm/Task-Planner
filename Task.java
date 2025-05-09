package javaproject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Task {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String priority; // High, Medium, Low

    public Task(String title, String description, LocalDateTime deadline, String priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public long hoursLeft() {
        return Duration.between(LocalDateTime.now(), deadline).toHours();
    }

    public String getPriority() {
        return priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Title: " + title +
               "\nDescription: " + description +
               "\nDeadline: " + deadline.format(formatter) +
               "\nPriority: " + priority +
               "\nHours Left: " + hoursLeft() + " hours\n";
    }
}

