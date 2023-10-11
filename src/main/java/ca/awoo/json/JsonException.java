package ca.awoo.json;

/**
 * A generic exception for all JSON-related exceptions.
 * This allows you to catch all JSON-related exceptions with one catch block.
 */
public class JsonException extends Exception {

    public JsonException() {
        super();
    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
