// TodoListApp.java
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {
    public static void main(String[] args) {
        ArrayList<String> todoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nTodo List");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    todoList.add(task);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.println("\nTasks:");
                    if (todoList.isEmpty()) {
                        System.out.println("No tasks");
                    } else {
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println((i + 1) + ". " + todoList.get(i));
                        }
                    }
                    break;
                case 3:
                    if (todoList.isEmpty()) {
                        System.out.println("No tasks to mark as completed.");
                    } else {
                        System.out.print("Enter task number to mark as completed: ");
                        int taskNumber = scanner.nextInt();
                        if (taskNumber > 0 && taskNumber <= todoList.size()) {
                            String completedTask = todoList.remove(taskNumber - 1);
                            System.out.println("Task \"" + completedTask + "\" marked as completed.");
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
