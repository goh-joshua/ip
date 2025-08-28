import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    private LocalDate dueDate;

    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +")";
    }
    @Override
    public String getSaveString() {
        return "D|" + super.getSaveString() + super.getTaskString() + "|" + dueDate.toString();
    }
}
