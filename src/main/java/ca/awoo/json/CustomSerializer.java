package ca.awoo.json;

import ca.awoo.json.types.JsonValue;

public interface CustomSerializer {
    public JsonValue<?> serialize();
    public void deserialize(JsonValue<?> json);
}
