import java.util.ArrayList;

public class toDoList {
    private ArrayList<Task> tasks = new ArrayList<>();

    // Initializer
    public toDoList() {

    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("added:");
        System.out.println(task);
        System.out.println("Burh, you have " + tasks.size() + " tasks now!");
    }

    public void completeTask(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).complete();
            System.out.println("Bokay, you have done this task 👍");
            System.out.println("    " + tasks.get(i - 1));
        }
    }

    public void uncompleteTask(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).uncomplete();
            System.out.println("Burh, seriously?");
            System.out.println("    " + tasks.get(i - 1));
        }
    }

    public void orderedPrint() {
        String done;
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

}
