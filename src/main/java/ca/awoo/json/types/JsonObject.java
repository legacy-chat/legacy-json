package ca.awoo.json.types;

import java.util.HashMap;
import java.util.Map;

public class JsonObject extends JsonValue<Map<String, JsonValue<?>>> {

    public JsonObject() {
        this(new HashMap<String, JsonValue<?>>());
    }

    public JsonObject(Map<String, JsonValue<?>> values) {
        super(values);
    }

    public void put(String key, JsonValue<?> value) {
        this.value.put(key, value);
    }

    public JsonValue<?> get(String key) {
        return this.value.get(key);
    }
}
