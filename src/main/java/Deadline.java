import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    private String dueDate;

    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate +")";
    }
    @Override
    public String getSaveString() {
        return "D|" + super.getSaveString() + super.getTaskString() + "|" + dueDate;
    }
}
