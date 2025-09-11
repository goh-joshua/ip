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
     * @return Task addition confimration string
     */
    public String addTask(Task task) {
        int initialSize = tasks.size();
        tasks.add(task);
        int newSize =tasks.size();
        assert initialSize + 1 == newSize;
        return ("added:" 
        + task.toString() + "\n"
        + "Burh, you have " + tasks.size() + " tasks now!");
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
     * Deletes the task at the given index and returns confirmation string.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     * @return Task deletion confirmation string.
     */
    public String deleteTask(int i) {
        try {
            String s1 = ("Goodbye! " + tasks.get(i - 1));
            tasks.remove(i - 1);
            String s2 = ("Burh, you have " + tasks.size() + " tasks now!");
            return s1 + "\n" + s2;
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    /**
     * Completes task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     * @return Task completion confirmation string.
     */
    public String completeTask(int i) {
        try {
            tasks.get(i - 1).complete();
            return "Bokay, you have done this task 👍"
                    + "    " + tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    /**
     * Completes most recent task.
     */
    public void completeMostRecent() {
        assert !tasks.isEmpty();
        tasks.get(tasks.size() - 1).complete();
    }

    /**
     * Uncompletes task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     * @return Task uncompletion confirmation string.
     */
    public String uncompleteTask(int i) {
        try {
            tasks.get(i - 1).uncomplete();
            return "Burh, seriously?"
                    + "    " + tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BurhException("Invalid index");
        }
    }

    /**
     * Prints all tasks in the list in order.
     *
     * @return List of all task in string.
     */
    public String orderedPrint() {
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            all.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return all.toString();
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

    /**
     * Searches the task list for tasks containing the given keyword,
     * creates a TaskList that contains them and then
     * prints them orderly.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return List of tasks contain keyword in a string.
     */
    public String findKeywordInTasks(String keyword) {
        TaskList results = new TaskList();
        this.tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .forEach(results::addTaskQuiet);
        return results.orderedPrint();
    }

    /**
     * Sorts the task list based on priority in the task arraylist.
     *
     * @return List of tasks in the new sorted list in a string.
     */

    public String sortChrono() {
        this.tasks.sort(null);
        return orderedPrint();
    }

}
