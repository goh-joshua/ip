package burh;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

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

    public void save(List<String> taskStringList) throws IOException {
        Path folder = filePath.getParent();
        if (folder != null && !Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        Files.write(filePath, taskStringList, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}