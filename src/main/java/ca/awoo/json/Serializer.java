package ca.awoo.json;

import ca.awoo.json.types.JsonValue;
import ca.awoo.json.serializers.*;

/**
 * A serializer is used to convert an object to and from a JsonValue.
 * <p>
 * The serializer is used by the {@link Json} class to serialize and deserialize objects.
 * To create a custom serializer, simply implement this interface and register it with the {@link Json} class.
 * </p>
 * @param <T> The type of object to serialize.
 * @see Json
 * @see JsonValue
 * @see JsonSerializationException
 * @see JsonDeserializationException
 * @see BooleanSerializer
 * @see IntegerSerializer
 * @see StringSerializer
 * @see ShortSerializer
 * @see LongSerializer
 * @see FloatSerializer
 * @see DoubleSerializer
 * @see CharacterSerializer
 * @see ByteSerializer
 */
public interface Serializer<T> {
    /**
     * Serializes an object to a {@link JsonValue}.
     * @param obj The object to serialize.
     * @param clazz The class of the object to serialize.
     * @return The serialized object.
     * @throws JsonSerializationException If the object could not be serialized.
     */
    public JsonValue<?> serialize(T obj, Class<? extends T> clazz) throws JsonSerializationException;

    /**
     * Deserializes a {@link JsonValue} to an object.
     * @param json The {@link JsonValue} to deserialize.
     * @param clazz The class of the object to deserialize.
     * @return The deserialized object.
     * @throws JsonDeserializationException If the {@link JsonValue} could not be deserialized.
     */
    public T deserialize(JsonValue<?> json, Class<? extends T> clazz) throws JsonDeserializationException;
}
