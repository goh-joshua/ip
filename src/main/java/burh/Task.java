package burh;

/**
 * Represents a general task with a description and completion status.
 */
public class Task {
    private String taskString;
    private boolean completed = false;

    /**
     * Creates a task with the given description.
     *
     * @param s Description of the task.
     */
    public Task(String s) {
        this.taskString = s;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        completed = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void uncomplete() {
        completed = false;
    }

    @Override
    public String toString() {
        String done;
        if (completed) {
            done = "❌";
        } else {
            done = "  ";
        }
        return "[" + done + "] " + this.taskString;
    }

    /**
     * Returns the task.
     */
    public String getTaskString() {
        return this.taskString;
    }

    /**
     * Returns the task formatted in the save format.
     */
    public String getSaveString() {
        return (completed) ? "T|" : "F|";
    }

}
