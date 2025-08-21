public class Task {
    private String taskString;
    private boolean completed = false;

    public Task(String s) {
        this.taskString = s;
    }

    public void complete() {
        completed = true;
    }

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
}
