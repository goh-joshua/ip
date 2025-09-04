package burh;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Handles saving and loading tasks to local storage.
 */
public class Storage {
    private Path filePath;

    /**
     * Creates a Storage instance with the given file path.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the save file.
     * Returns an empty task list if the file does not exist.
     *
     * @return A TaskList containing loaded tasks.
     * @throws BurhException If the file cannot be read or the data is corrupted.
     */
    public TaskList load() throws BurhException {
        TaskList tasks = new TaskList();

        try {
            if (!Files.exists(filePath)) {
                return tasks;
            }

            List<String> saveData = Files.readAllLines(filePath);
            for (String line : saveData) {
                String[] parts = line.split("\\|");
                char type = parts[0].charAt(0);
                boolean marked = parts[1].charAt(0) == 'T';
                String taskDescription = parts[2];

                Task task = null;
                switch (type) {
                    case 'T':
                        task = new Todo(taskDescription);
                        break;
                    case 'D':
                        task = new Deadline(taskDescription, parts[3]);
                        break;
                    case 'E':
                        task = new Event(taskDescription, parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    tasks.addTaskQuiet(task);
                    if (marked) {
                        tasks.completeMostRecent();
                    }
                }
            }
        } catch (IOException e) {
            throw new BurhException("Error loading data from file");
        } catch (Exception e) {
            throw new BurhException("Corrupted data");
        }

        return tasks;
    }

    /**
     * Saves tasks from the current task list into the file path.
     *
     * @throws IOException If the file cannot be read or the data is corrupted.
     */
    public void save(List<String> taskStringList) throws IOException {
        Path folder = filePath.getParent();
        if (folder != null && !Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        Files.write(filePath, taskStringList, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
