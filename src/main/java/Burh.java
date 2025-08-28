import java.io.IOException;
import java.nio.file.*;
import java.util.*;


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
            try {
                // Gets user input
                String input = scanner.nextLine();
                String[] command = input.split("\\s+"); // Splits the input
                try {
                    Command c = Command.valueOf(command[0].toUpperCase());
                    switch (c) {

                        case BYE: {
                            stop = true; // Ends the loop
                            System.out.println("Burh, goodbye!");
                            // Creates save folder and save data
                            Path folder = Paths.get("data");
                            Files.createDirectory(folder);
                            List<String> l = List.of("test", "testt");
                            Files.write(folder.resolve("test.txt"), l, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                            break;
                        }

                        case LIST: {
                            tdl.orderedPrint();
                            break;
                        }

                        case MARK: {
                            try {
                                int index = Integer.parseInt(command[1]);
                                tdl.completeTask(index);
                            } catch (NumberFormatException e) {
                                throw new BurhException("Burh..., give a proper index...");
                            }
                            break;
                        }

                        case UNMARK: {
                            try {
                                int index = Integer.parseInt(command[1]);
                                tdl.uncompleteTask(index);
                            } catch (NumberFormatException e) {
                                throw new BurhException("Burh..., give a proper index...");
                            }
                            break;
                        }

                        case DELETE: {
                            try {
                                int index = Integer.parseInt(command[1]);
                                tdl.deleteTask(index);
                            } catch (NumberFormatException e) {
                                throw new BurhException("Burh..., give a proper index...");
                            }
                            break;
                        }

                        case TODO: {
                            if (command.length == 1) {
                                throw new BurhException("Burh, what do u want to do?!?!");
                            } else {
                                Task task = new Todo(input.replaceFirst("todo ", ""));
                                tdl.addTask(task);
                            }
                            break;
                        }

                        case DEADLINE: {
                            String[] parts = input.split("/");
                            Task task = new Deadline(parts[0].replaceFirst("deadline ", ""),
                                    parts[1].replaceFirst("by ", ""));
                            tdl.addTask(task);
                            break;
                        }

                        case EVENT: {
                            String[] parts = input.split("/");
                            Task task = new Event(parts[0].replaceFirst("event ", ""),
                                    parts[1].replaceFirst("from ", ""),
                                    parts[2].replaceFirst("to ", ""));
                            tdl.addTask(task);
                            break;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    throw new BurhException("I do not understand your command");
                } catch (IOException e) {
                   e.printStackTrace();
                }
            } catch (BurhException e) {
                System.out.println(" " + e.getMessage());
            }

        printLines();

        }
    }
}
