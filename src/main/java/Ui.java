import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Bello! I'm Burh");
        System.out.println("What can I do for you?");
        printLines();
    }

    public void showGoodbye() {
        System.out.println("Burh, goodbye!");
    }

    public void showLoadingError() {
        System.out.println("Save data not found");
    }

    public void showCorruptedDataError() {
        System.out.println("Corrupted data");
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printLines() {
        System.out.println("_-".repeat(20));
    }
}