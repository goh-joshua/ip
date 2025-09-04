package burh;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Creates a deadline task with the given description and due date.
     *
     * @param task Description of the deadline task.
     * @param dueDate Due date in yyyy-MM-dd format.
     */
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String getSaveString() {
        return "D|" + super.getSaveString() + super.getTaskString() + "|" + dueDate.toString();
    }
}
