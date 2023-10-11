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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : value.keySet()) {
            sb.append("\"" + JsonString.escape(key) + "\": " + value.get(key).toString() + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}
