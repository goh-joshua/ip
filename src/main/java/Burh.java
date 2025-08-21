import java.util.Scanner;


public class Burh {

    // Function to print lines
    public static void printLines() {
        System.out.println("_-".repeat(20));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creates a scanner to detect user input
        toDoList tdl = new toDoList();
        boolean stop = false; // To help detect when to end to program

        System.out.println("Bello! I'm Burh");
        System.out.println("What can I do for you?");
        printLines();

        while (!stop) {
            // Gets user input
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                stop = true; // Ends the loop
                System.out.println("Burh, goodbye!");

            } else if (input.equals("list")) {
                tdl.orderedPrint();

            } else if (input.split("\\s+").length >= 2) { // Checks for possible commands
                String[] command = input.split("\\s+"); // Splits the input

                if (command[0].equals("mark")) {
                    try {
                        int index = Integer.parseInt(command[1]);
                        tdl.completeTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
                    }

                } else if (command[0].equals("unmark")) {
                    try {
                        int index = Integer.parseInt(command[1]);
                        tdl.uncompleteTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
                    }

                } else if (command[0].equals("todo")) {
                    Task task = new Todo(input.replaceFirst("todo ", ""));
                    tdl.addTask(task);

                } else if (command[0].equals("deadline")) {
                    String[] parts =  input.split("/");
                    Task task = new Deadline(parts[0].replaceFirst("deadline ", ""),
                                                parts[1].replaceFirst("by ", ""));
                    tdl.addTask(task);

                } else if (command[0].equals("event")) {
                    String[] parts =  input.split("/");
                    Task task = new Event(parts[0].replaceFirst("event ", ""),
                            parts[1].replaceFirst("from ", ""),
                            parts[2].replaceFirst("to ", ""));
                    tdl.addTask(task);
                }
            }
            printLines();
        }
    }
}
