package ca.awoo.json.types;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a JSON object.
 * A JSON object is a collection of key-value pairs.
 * The keys are strings, and the values are any JSON value.
 * The keys must be unique.
 * The order of the key-value pairs is not guaranteed.
 */
public class JsonObject extends JsonValue<Map<String, JsonValue<?>>> {

    /**
     * Creates a new empty JsonObject.
     */
    public JsonObject() {
        this(new HashMap<String, JsonValue<?>>());
    }

    /**
     * Creates a new JsonObject with the given values.
     * @param values The values to add to the JsonObject.
     */
    public JsonObject(Map<String, JsonValue<?>> values) {
        super(values);
    }

    /**
     * Adds a key-value pair to the JsonObject.
     * @param key The key of the pair.
     * @param value The value of the pair.
     */
    public void put(String key, JsonValue<?> value) {
        this.value.put(key, value);
    }

    /**
     * Gets the value associated with the given key.
     * @param key The key to get the value of.
     * @return The value associated with the given key.
     */
    public JsonValue<?> get(String key) {
        return this.value.get(key);
    }

    /**
     * Gets the string representation of the json object.
     * As with the toString() of all JsonValues, this will output valid JSON.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : value.keySet()) {
            sb.append("\"" + JsonString.escape(key) + "\": " + value.get(key).toString() + ", ");
        }
        if(sb.length() > 1){
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
}
