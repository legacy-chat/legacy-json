package ca.awoo.json.types;

/**
 * A generic JSON value. This is the base of the intermediate representation of JSON.
 * All JSON values extend this class.
 * All JSON text is parsed into a JsonValue at some point.
 * All serialized objects are converted into a JsonValue at some point.
 * @param <T> The type of the value.
 */
public abstract class JsonValue<T> {
    /**
     * The value of the JSON value.
     */
    protected T value;

    /**
     * Creates a new JSON value.
     * @param value The value of the JSON value.
     */
    public JsonValue(T value) {
        this.value = value;
    }

    /**
     * Gets the value of the JSON value.
     * @return The value of the JSON value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value of the JSON value.
     * @param val The value of the JSON value.
     */
    public void setValue(T val) {
        value = val;
    }

    /**
     * Gets the string representation of the JSON value.
     * This should always be valid JSON.
     */
    public String toString() {
        return value.toString();
    }
}
