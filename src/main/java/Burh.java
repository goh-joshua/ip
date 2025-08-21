import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Burh {
    // To store the history of the user's input
    static ArrayList<String> history = new ArrayList<>();

    // Function to print lines
    public static void printLines() {
        System.out.println("_-".repeat(20));
    }

    // Function to print an indexed list
    public static void orderedPrint(ArrayList<?> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println((i + 1) + ". " + array.get(i));
        }
    }

    public static void main(String[] args) {
        // Creates a scanner to detect user input in order to reply
        Scanner scanner = new Scanner(System.in);
        // To help detect when to end to program
        boolean stop = false;

        System.out.println("Bello! I'm Burh");
        System.out.println("What can I do for you?");
        printLines();

        while (!stop) {
            // Gets user input
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Ends the loop
                stop = true;
                System.out.println("Burh, goodbye!");
            } else if (input.equals("list")) {
                // Prints the history
                orderedPrint(history);
            } else {
                System.out.println("added: " + input);
                history.add(input);
            }
            printLines();
        }
    }
}
