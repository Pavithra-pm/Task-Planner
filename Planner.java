package javaproject;
import java.util.*;
import java.time.LocalDateTime;

public class Planner {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("📅 Smart Daily Planner");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Smart Suggest");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> smartSuggest();
                case 4 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addTask() {
        sc.nextLine(); // clear buffer
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Deadline (yyyy-MM-ddTHH:mm): ");
        String dateInput = sc.nextLine();
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Try again.");
            return;
        }
        System.out.print("Enter Priority (High/Medium/Low): ");
        String priority = sc.nextLine();
        tasks.add(new Task(title, desc, deadline, priority));
        System.out.println("Task added successfully.");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        tasks.stream()
             .sorted(Comparator
                     .comparing(Task::hoursLeft)
                     .thenComparing(t -> switch (t.getPriority()) {
                         case "High" -> 1;
                         case "Medium" -> 2;
                         default -> 3;
                     }))
             .forEach(t -> {
                 System.out.println("-------------------------------");
                 System.out.println(t);
             });
    }

    static void smartSuggest() {
        Optional<Task> suggested = tasks.stream()
            .filter(t -> t.hoursLeft() > 0)
            .min(Comparator
                .comparing(Task::hoursLeft)
                .thenComparing(t -> switch (t.getPriority()) {
                    case "High" -> 1;
                    case "Medium" -> 2;
                    default -> 3;
                }));

        if (suggested.isPresent()) {
            System.out.println("\n💡 Smart Suggestion:");
            System.out.println(suggested.get());
        } else {
            System.out.println("No upcoming tasks found.");
        }
    }
}

