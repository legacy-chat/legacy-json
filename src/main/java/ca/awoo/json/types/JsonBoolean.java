package ca.awoo.json.types;

/**
 * A JSON boolean.
 */
public class JsonBoolean extends JsonValue<Boolean> {

    /**
     * Creates a new JsonBoolean.
     * @param value The value of the JSON boolean.
     */
    public JsonBoolean(Boolean value) {
        super(value);
    }

    /**
     * Gets the string representation of the JSON boolean. This will be either "true" or "false".
     * @return The string representation of the JSON boolean.
     */
    @Override
    public String toString() {
        return value.toString();
    }
    
}
