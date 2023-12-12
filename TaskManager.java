import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

class Task {
    String name;
    String description;
    Date dueDate;

    Task(String name, String description, Date dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Name: " + name + "\nDescription: " + description + "\nDue Date: " + dateFormat.format(dueDate) + "\n";
    }
}

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    listTasks();
                    break;
                case 4:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Date dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            dueDate = parseDate(dateInput);
        }

        Task task = new Task(name, description, dueDate);
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        System.out.println("Tasks:");
        listTasks();

        int index = -1;
        while (index < 0 || index >= tasks.size()) {
            System.out.print("Enter the index of the task to remove: ");
            index = getIntInput();

            if (index < 0 || index >= tasks.size()) {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        }

        tasks.remove(index);
        System.out.println("Task removed successfully!");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Index " + i + ":\n" + tasks.get(i));
            }
        }
    }

    private static Date parseDate(String dateInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private static int getIntInput() {
        int input = -1;
        while (true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the invalid input
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return input;
    }
}
