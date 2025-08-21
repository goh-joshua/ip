import java.util.Scanner;


public class Burh {
    /**
     * Function to print lines
     */
    public static void printLines() {
        System.out.println("_-".repeat(20));
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
            //Gets user input
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                stop = true;
                System.out.println("Burh, goodbye!");
            } else {
                System.out.println(input);
            }
            printLines();
        }
    }
}
