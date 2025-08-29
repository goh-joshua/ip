package burh;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {

    }

    /**
     * Adds a task to the list and prints confirmation to the user.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("added:");
        System.out.println(task);
        System.out.println("Burh, you have " + tasks.size() + " tasks now!");
    }

    /**
     * Adds a task to the list without printing confirmation to the user.
     *
     * @param task The task to add.
     */
    public void addTaskQuiet(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     */
    public void deleteTask(int i) {
        try {
            System.out.println("Goodbye! " + tasks.get(i - 1));
            tasks.remove(i - 1);
            System.out.println("Burh, you have " + tasks.size() + " tasks now!");
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    /**
     * Completes task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     */
    public void completeTask(int i) {
        try {
            if (i >= 1 && i <= tasks.size()) {
                tasks.get(i - 1).complete();
                System.out.println("Bokay, you have done this task 👍");
                System.out.println("    " + tasks.get(i - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    /**
     * Completes most recent task.
     */
    public void completeMostRecent() {
        tasks.get(tasks.size() - 1).complete();
    }

    /**
     * Uncompletes task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     */
    public void uncompleteTask(int i) {
        try {
            if (i >= 1 && i <= tasks.size()) {
                tasks.get(i - 1).uncomplete();
                System.out.println("Burh, seriously?");
                System.out.println("    " + tasks.get(i - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    /**
     * Prints all tasks in the list in order.
     */
    public void orderedPrint() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

    /**
     * Returns a list of strings representing tasks in their save format.
     *
     * @return List of task strings.
     */
    public List<String> getStringList() {
        List<String> l = new ArrayList<>();
        for (Task t : this.tasks) {
            l.add(t.getSaveString());
        }
        return l;
    }

}
