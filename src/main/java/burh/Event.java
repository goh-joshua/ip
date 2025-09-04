package burh;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start date and end date.
 */
public class Event extends Task {
    private LocalDate toDate;
    private LocalDate fromDate;

    /**
     * Creates an event task with the given description, start date and end date.
     *
     * @param task Description of the event task.
     * @param from Start date in yyyy-MM-dd format.
     * @param to End date in yyyy-MM-dd format.
     */
    public Event(String task, String from, String to) {
        super(task);
        this.toDate = LocalDate.parse(to);
        this.fromDate = LocalDate.parse(from);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getSaveString() {
        return "E|" + super.getSaveString() + super.getTaskString() + "|"
                + fromDate.toString() + "|" + toDate.toString();
    }
}
