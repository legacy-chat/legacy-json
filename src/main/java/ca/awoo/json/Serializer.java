package ca.awoo.json;

import ca.awoo.json.types.JsonValue;

public interface Serializer<T> {
    public JsonValue<?> serialize(T obj, Class<? extends T> clazz) throws JsonSerializationException;
    public T deserialize(JsonValue<?> json, Class<? extends T> clazz) throws JsonDeserializationException;
}
