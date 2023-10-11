package ca.awoo.json.types;

/**
 * A JSON number.
 * This is a wrapper around a Java Number. As such, it defines no concrete implementation for how that number is stored.
 */
public class JsonNumber extends JsonValue<Number> {

    /**
     * Creates a new JsonNumber.
     * @param value The value of the JSON number.
     */
    public JsonNumber(Number value) {
        super(value);
    }
    
    /**
     * Gets the string representation of the JSON number.
     * This will be the same as the string representation of the underlying Number.
     * This may change in the future to mantain the rule that all JsonValues have valid JSON representations returned by toString().
     * @return The string representation of the JSON number.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
