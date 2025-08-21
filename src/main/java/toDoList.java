import java.util.ArrayList;

public class toDoList {
    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<Boolean> completed = new ArrayList<>();

    // Initializer
    public toDoList() {

    }

    public void addTask(String task) {
        tasks.add(task);
        completed.add(false);
        System.out.println("added: " + task);
    }

    public void completeTask(int i) {
        if (i >= 1 && i < completed.size()) {
            completed.set(i - 1, true);
            System.out.println("Bokay, you have done this task 👍");
            System.out.println("    [❌] " + tasks.get(i-1));
        }
    }

    public void uncompleteTask(int i) {
        if (i >= 1 && i < completed.size()) {
            completed.set(i - 1, false);
            System.out.println("Burh, seriously?");
            System.out.println("    [  ] " + tasks.get(i-1));
        }
    }

    public void orderedPrint() {
        String done;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.completed.get(i)) {
                done = "❌";
            } else {
                done = "  ";
            }
            System.out.println((i + 1) + ". [" + done + "] " + this.tasks.get(i));
        }
    }

}
