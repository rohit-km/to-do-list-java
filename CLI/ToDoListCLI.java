
import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + title;
    }
}

public class ToDoListCLI {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the To-Do List Application (CLI)");
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addTask();
                case 2 -> listTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> removeTask();
                case 5 -> exitApplication();
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        tasks.add(new Task(title));
        System.out.println("Task added successfully!");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("--- To-Do List ---");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void markTaskAsCompleted() {
        listTasks();
        System.out.print("Enter task number to mark as completed: ");
        int taskNumber = Integer.parseInt(scanner.nextLine()) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void removeTask() {
        listTasks();
        System.out.print("Enter task number to remove: ");
        int taskNumber = Integer.parseInt(scanner.nextLine()) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.remove(taskNumber);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void exitApplication() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }
}
