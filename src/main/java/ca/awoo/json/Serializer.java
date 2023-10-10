package ca.awoo.json;

import ca.awoo.json.types.JsonValue;

public interface Serializer<T> {
    public JsonValue<?> serialize(T obj);
    public T deserialize(JsonValue<?> json) throws JsonDeserializationException;
}
