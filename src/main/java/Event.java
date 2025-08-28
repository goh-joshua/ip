
public class Event extends Task {
    private String To;
    private String From;

    public Event(String task, String From, String To) {
        super(task);
        this.To = To;
        this.From = From;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + From + " to: " + To + ")";
    }

    @Override
    public String getSaveString() {
        return "E|" + super.getSaveString() + super.getTaskString() + "|" + From + "|" + To;
    }
}