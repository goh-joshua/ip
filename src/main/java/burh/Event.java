package burh;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start date and end date.
 */
public class Event extends Task {
    private LocalDate To;
    private LocalDate From;

    /**
     * Creates an event task with the given description, start date and end date.
     *
     * @param task Description of the event task.
     * @param From Start date in yyyy-MM-dd format.
     * @param To End date in yyyy-MM-dd format.
     */
    public Event(String task, String From, String To) {
        super(task);
        this.To = LocalDate.parse(To);
        this.From = LocalDate.parse(From);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + From.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + To.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getSaveString() {
        return "E|" + super.getSaveString() + super.getTaskString() + "|" + From.toString() + "|" + To.toString();
    }
}