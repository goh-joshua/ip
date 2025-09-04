package burh;

/**
 * Represents an exception specific to the Burh application.
 * Extends {@code RuntimeException} so it is unchecked.
 */

public class BurhException extends RuntimeException {

    /**
     * Constructs a {@code BurhException} with the specified detail message.
     *
     * @param message the detail message associated with this exception
     */
    public BurhException(String message) {
        super(message);
    }
}
