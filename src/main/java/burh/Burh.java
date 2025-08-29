package burh;
import java.io.IOException;

/**
 * Entry point of the Burh chatbot.
 * Handles initialization of storage, tasks, and user interface,
 * and runs the main event loop to process commands.
 */
public class Burh {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Burh instance with the given file path.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     */
    public Burh(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (BurhException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Reads user input, interprets and executes commands until user ends.
     */
    public void run() {
        ui.showWelcome();
        boolean stop = false;
        while (!stop) {
            try {
                String input = ui.readCommand();
                Command command = Parser.getCommand(input);

                switch (command) {
                    case BYE:
                        stop = true;
                        ui.showGoodbye();
                        // Save data before exiting
                        try {
                            storage.save(tasks.getStringList());
                        } catch (IOException e) {
                            ui.showError("Error saving data: " + e.getMessage());
                        }
                        break;

                    case LIST:
                        tasks.orderedPrint();
                        break;

                    case MARK:
                        int markIndex = Parser.parseIndex(input);
                        tasks.completeTask(markIndex);
                        break;

                    case UNMARK:
                        int unmarkIndex = Parser.parseIndex(input);
                        tasks.uncompleteTask(unmarkIndex);
                        break;

                    case DELETE:
                        int deleteIndex = Parser.parseIndex(input);
                        tasks.deleteTask(deleteIndex);
                        break;

                    case TODO:
                        Task todoTask = Parser.parseTodoTask(input);
                        tasks.addTask(todoTask);
                        break;

                    case DEADLINE:
                        Task deadlineTask = Parser.parseDeadlineTask(input);
                        tasks.addTask(deadlineTask);
                        break;

                    case EVENT:
                        Task eventTask = Parser.parseEventTask(input);
                        tasks.addTask(eventTask);
                        break;

                    case FIND:
                        tasks.findKeywordInTasks(Parser.parseKeyword(input));
                        break;
                }
            } catch (BurhException e) {
                ui.showError(e.getMessage());
            }

            ui.printLines();
        }
    }

    public static void main(String[] args) {
        new Burh("data/test.txt").run();
    }
}
