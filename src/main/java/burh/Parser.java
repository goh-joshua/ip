package burh;
public class Parser {

    public static Command getCommand(String s) {
        try {
            return Command.valueOf(s.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BurhException("I do not understand your command");
        }
    }

    public static int parseIndex(String fullCommand) throws BurhException {
        String[] commandParts = fullCommand.split("\\s+");

        if (commandParts.length != 2) {
            throw new BurhException("Burh..., give a proper index...");
        }

        try {
            return Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            throw new BurhException("Burh..., give a proper index...");
        }
    }

    public static Task parseTodoTask(String fullCommand) throws BurhException {

        return new Todo(fullCommand.replaceFirst("todo ", ""));
    }

    public static Task parseDeadlineTask(String fullCommand) throws BurhException {
        String[] parts = fullCommand.split(" /by ");

        if (parts.length != 2) {
            throw new BurhException("Burh, use the format: deadline <description> /by <date>");
        }

        return new Deadline(parts[0].replaceFirst("deadline ", ""), parts[1]);
    }

    public static Task parseEventTask(String fullCommand) throws BurhException {
        String[] parts1 = fullCommand.split(" /from ");

        if (parts1.length != 2) {
            throw new BurhException("Burh, use the format: event <description> /from <start> /to <end>");
        }

        String[] parts2 = parts1[1].split(" /to ");

        if (parts2.length != 2) {
            throw new BurhException("Burh, use the format: event <description> /from <start> /to <end>");
        }

        return new Event(parts1[0].replaceFirst("event ", ""), parts2[0], parts2[1]);
    }

    /**
     * Parses a find command and returns the keyword the user wants to find.
     *
     * @param fullCommand the full command string entered by the user starting with "find".
     * @return a String of the keyword.
     * @throws BurhException if the command format is invalid
     */
    public static String parseKeyword(String fullCommand) throws BurhException {
        String[] parts = fullCommand.split(" ");

        if (parts.length != 2) {
            throw new BurhException("Burh. Enter 1 keyword. ");
        }

        return parts[1];
    }


}
