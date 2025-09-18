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
        int newSize = tasks.size();
        assert initialSize + 1 == newSize;
        
        String[] reactions = {
            "Burh... fine. Added: " + task.toString() + "\n"
                    + "Burh, now you have " + tasks.size() + " things to not do.",
                    
            "*sigh* Burh, if I must... " + task.toString() + "\n"
                    + "Burh, that's " + tasks.size() + " tasks now. Not that I care.",
                    
            "Burh, really? Another one? " + task.toString() + "\n"
                    + tasks.size() + " tasks, burh. You're really pushing it."
        };
        
        return reactions[(int)(Math.random() * reactions.length)];
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
            Task removedTask = tasks.get(i - 1);
            tasks.remove(i - 1);
            
            String[] reactions = {
                "Burh, deleted " + removedTask + 
                "\n" + tasks.size() + " tasks left, burh. Not that it matters.",
                
                "Burh, fine. Removed: " + removedTask + 
                "\nBurh, down to " + tasks.size() + " tasks. Whatever.",
                
                "Burh... *sigh* Deleted " + removedTask + 
                "\n" + tasks.size() + " tasks remaining, burh. Not that you care."
            };
            
            return reactions[(int)(Math.random() * reactions.length)];
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
            String[] reactions = {
                "Burh... you actually did something for once.\n" + "    " + tasks.get(i - 1) + "\nBurh, I'm shocked.",
                "*checks watch* Burh, took you long enough.\n" + "    " + tasks.get(i - 1) + "\nBurh, what's next?",
                "Burh... a completed task. You want a medal or something?\n" + "    " + tasks.get(i - 1) + "\nBurh, whatever."
            };
            return reactions[(int)(Math.random() * reactions.length)];
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
            String[] reactions = {
                "Burh... of course you're unmarking it. Why am I not surprised?\n" + "    " + tasks.get(i - 1) + "\nBurh, typical.",
                "*sigh* Burh, unmarked. Not like I expected any better.\n" + "    " + tasks.get(i - 1) + "\nBurh, why do I even bother?",
                "Burh... shocking. You couldn't even keep a task completed.\n" + "    " + tasks.get(i - 1) + "\nBurh, I'm not even mad, just disappointed."
            };
            return reactions[(int)(Math.random() * reactions.length)];
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
        if (tasks.isEmpty()) {
            return "Burh... your list is empty. Must be nice having no responsibilities.\nBurh, must be nice.";
        }
        
        String[] intros = {
            "Burh... *sigh* Here are your " + tasks.size() + " tasks. Not that you'll do them.\n",
            "Burh, you want me to list all " + tasks.size() + " tasks? Fine. But make it quick.\n",
            "Burh, " + tasks.size() + " tasks you're probably going to ignore. Here:\n"
        };
        
        StringBuilder s = new StringBuilder(intros[(int)(Math.random() * intros.length)]);
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return s.toString().trim();
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
     * This method uses Java 8's Stream API to filter the tasks by
     * description and then add them to a new TaskList.
     * This new TaskList is then printed using the orderedPrint() method.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return List of tasks contain keyword in a string.
     */
    public String findKeywordInTasks(String keyword) {
        // Create a new TaskList to store the filtered tasks
        TaskList results = new TaskList();

        // Filter the tasks by description and add them to the new TaskList
        this.tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .forEach(results::addTaskQuiet);

        // Print the filtered tasks
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
