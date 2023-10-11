package ca.awoo.json;

/**
 * Thrown when an object cannot be serialized to JSON.
 */
public class JsonSerializationException extends JsonException {
    /**
     * The object that could not be serialized.
     */
    public Object obj;

    /**
     * Creates a new JsonSerializationException.
     * Uses the default message "Could not serialize object: {obj.toString()} with type: {obj.getClass().getName()}"
     * @param obj The object that could not be serialized.
     */
    public JsonSerializationException(Object obj) {
        super("Could not serialize object: " + obj.toString() + " with type: " + obj.getClass().getName());
        this.obj = obj;
    }

    /**
     * Creates a new JsonSerializationException with a custom message.
     * The message will still contain the object and its type.
     * The message displayed will be "{message}: {obj.toString()} with type: {obj.getClass().getName()}"
     * @param obj The object that could not be serialized.
     * @param message The message to display.
     */
    public JsonSerializationException(Object obj, String message) {
        super(message + ": " + obj.toString() + " with type: " + obj.getClass().getName());
        this.obj = obj;
    }

    /**
     * Creates a new JsonSerializationException with a cause.
     * Uses the default message "Could not serialize object: {obj.toString()} with type: {obj.getClass().getName()}"
     * @param obj The object that could not be serialized.
     * @param cause The cause of this exception.
     */
    public JsonSerializationException(Object obj, Throwable cause) {
        super("Could not serialize object: " + obj.toString() + " with type: " + obj.getClass().getName(), cause);
        this.obj = obj;
    }

    /**
     * Creates a new JsonSerializationException with a custom message and a cause.
     * The message will still contain the object and its type.
     * The message displayed will be "{message}: {obj.toString()} with type: {obj.getClass().getName()}"
     * @param obj The object that could not be serialized.
     * @param message The message to display.
     * @param cause The cause of this exception.
     */
    public JsonSerializationException(Object obj, String message, Throwable cause) {
        super(message + ": " + obj.toString() + " with type: " + obj.getClass().getName(), cause);
        this.obj = obj;
    }
}
