package burh;

import java.time.format.DateTimeFormatter;

/**
 * Represents a general task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean isCompleted = false;
    protected static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a task with the given description.
     *
     * @param s Description of the task.
     */
    public Task(String s) {
        this.description = s;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        isCompleted = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void uncomplete() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        String done;
        if (isCompleted) {
            done = "❌";
        } else {
            done = "  ";
        }
        return "[" + done + "] " + this.description;
    }

    /**
     * Returns the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task formatted in the save format.
     */
    public String getSaveString() {
        return (isCompleted) ? "T|" : "F|";
    }

}
