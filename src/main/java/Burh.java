import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class Burh {
    private static Path folder = Paths.get("data");
    // Function to print lines
    public static void printLines() {
        System.out.println("_-".repeat(20));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creates a scanner to detect user input
        toDoList tdl = new toDoList();
        boolean stop = false; // To help detect when to end to program
        try {
            List<String> save_data = Files.readAllLines(folder.resolve("test.txt"));
            for (String l : save_data) {
                String[] parts = l.split("\\[");
                char type = parts[1].charAt(0);
                boolean marked = parts[2].charAt(0) != ' ';
                String task;
                switch (type) {
                    case 'T': {
                        task = parts[2].split("] ")[1];
                        tdl.addTaskQuiet(new Todo(task));
                        if (marked) {
                            tdl.completeMostRecent();
                        }
                        break;
                    }
                    case 'D': {
                        String[] vars = parts[2].split("] ")[1].split(" \\(");
                        task = vars[0];
                        String by = vars[1].substring(3, vars[1].length() - 1);
                        tdl.addTaskQuiet(new Deadline(task, by));
                        if (marked) {
                            tdl.completeMostRecent();
                        }
                        break;
                    }
                    case 'E': {
                        String[] vars = parts[2].split("] ")[1].split(" \\(");
                        task = vars[0];
                        vars = vars[1].split("from: ")[1].split(" to: ");
                        String from = vars[0];
                        String to = vars[1].substring(0, vars[1].length() - 1);
                        tdl.addTaskQuiet(new Event(task, from, to));
                        if (marked) {
                            tdl.completeMostRecent();
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Save data not found");
        } catch (Exception e) {
            System.out.println("Corrupted data");
        }

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
                            if (!Files.exists(folder)) {
                                Files.createDirectory(folder);
                            }
                            Files.write(folder.resolve("test.txt"), tdl.getStringList(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
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
                            String[] parts = input.split(" /");
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
