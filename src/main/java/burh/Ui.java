package burh;

import java.util.Scanner;

/**
 * Handles all interactions with the user, including input and output.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a Ui instance and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Return the welcome message to the user.
     *
     * @return Welcome string.
     */
    public String showWelcome() {
        return "Bello! I'm Burh"
        + "\nWhat can I do for you?";
    }

    /**
     * Return the goodbye message to the user.
     *
     * @return Goodbye string.
     */
    public String showGoodbye() {
        return "Burh, goodbye!";
    }

    /**
     * Displays a message indicating that the save data could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Save data not found");
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a decorative line to separate sections of output.
     */
    public void printLines() {
        System.out.println("_-".repeat(20));
    }
}
