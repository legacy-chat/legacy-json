package ca.awoo.json;

import ca.awoo.json.types.JsonValue;

/**
 * Thrown when a JsonValue cannot be deserialized into a Java object.
 */
public class JsonDeserializationException extends JsonException {
    /**
     * The JsonValue that could not be deserialized.
     */
    public final JsonValue<?> json;

    /**
     * Creates a new JsonDeserializationException.
     * Uses the default message "Could not deserialize JSON: {json}"
     * @param json The JsonValue that could not be deserialized.
     */
    public JsonDeserializationException(JsonValue<?> json) {
        super("Could not deserialize JSON: " + json);
        this.json = json;
    }

    /**
     * Creates a new JsonDeserializationException with a custom message.
     * The message will still contain the JsonValue.
     * The message displayed will be "{message}: {json}"
     * @param json The JsonValue that could not be deserialized.
     * @param message The message to display.
     */
    public JsonDeserializationException(JsonValue<?> json, String message) {
        super(message + ": " + json);
        this.json = json;
    }

    /**
     * Creates a new JsonDeserializationException with a cause.
     * Uses the default message "Could not deserialize JSON: {json}"
     * @param json The JsonValue that could not be deserialized.
     * @param cause The cause of this exception.
     */
    public JsonDeserializationException(JsonValue<?> json, Throwable cause) {
        super("Could not deserialize JSON: " + json, cause);
        this.json = json;
    }

    /**
     * Creates a new JsonDeserializationException with a custom message and a cause.
     * The message will still contain the JsonValue.
     * The message displayed will be "{message}: {json}"
     * @param json The JsonValue that could not be deserialized.
     * @param message The message to display.
     * @param cause The cause of this exception.
     */
    public JsonDeserializationException(JsonValue<?> json, String message, Throwable cause) {
        super(message + ": " + json, cause);
        this.json = json;
    }
}
