package burh;
import java.util.*;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    // Initializer
    public TaskList() {

    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("added:");
        System.out.println(task);
        System.out.println("Burh, you have " + tasks.size() + " tasks now!");
    }

    public void addTaskQuiet(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int i) {
        try {
            System.out.println("Goodbye! " + tasks.get(i - 1));
            tasks.remove(i - 1);
            System.out.println("Burh, you have " + tasks.size() + " tasks now!");
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    public void completeTask(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).complete();
            System.out.println("Bokay, you have done this task 👍");
            System.out.println("    " + tasks.get(i - 1));
        }
    }

    public void completeMostRecent() {
        tasks.get(tasks.size() - 1).complete();
    }

    public void uncompleteTask(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).uncomplete();
            System.out.println("Burh, seriously?");
            System.out.println("    " + tasks.get(i - 1));
        }
    }

    public void orderedPrint() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

    public List<String> getStringList() {
        List<String> l = new ArrayList<>();
        for (Task t : this.tasks) {
            l.add(t.getSaveString());
        }
        return l;
    }

    /**
     * Searches the task list for tasks containing the given keyword,
     * creates a TaskList that contains them and then
     * prints them orderly.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findKeywordInTasks(String keyword) {
        TaskList l = new TaskList();
        for (Task t : this.tasks) {
            if (t.getTaskString().contains(keyword)) {
                l.addTaskQuiet(t);
            }
        }
        l.orderedPrint();
    }

}
