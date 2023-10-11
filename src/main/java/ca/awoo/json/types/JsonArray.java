package ca.awoo.json.types;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue<List<JsonValue<?>>> {

    public JsonArray() {
        this(new ArrayList<JsonValue<?>>());
    }

    public JsonArray(List<JsonValue<?>> value) {
        super(value);
    }

    public void add(JsonValue<?> value) {
        this.getValue().add(value);
    }

    public JsonValue<?> get(int index) {
        return this.getValue().get(index);
    }
}
