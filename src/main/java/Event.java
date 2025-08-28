import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDate To;
    private LocalDate From;

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