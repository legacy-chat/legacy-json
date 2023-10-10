package ca.awoo.json;

import ca.awoo.json.types.JsonValue;

public class JsonDeserializationException extends JsonException {
    public final JsonValue<?> json;

    public JsonDeserializationException(JsonValue<?> json) {
        super("Could not deserialize json: " + json);
        this.json = json;
    }

    public JsonDeserializationException(JsonValue<?> json, String message) {
        super(message + ": " + json);
        this.json = json;
    }

    public JsonDeserializationException(JsonValue<?> json, Throwable cause) {
        super("Could not deserialize json: " + json, cause);
        this.json = json;
    }

    public JsonDeserializationException(JsonValue<?> json, String message, Throwable cause) {
        super(message + ": " + json, cause);
        this.json = json;
    }
}
