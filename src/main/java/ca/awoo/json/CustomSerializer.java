package ca.awoo.json;

import ca.awoo.json.types.JsonValue;

/**
 * An interface for classes that can serialize and deserialize themselves.
 * <p>
 * This interface is used by the {@link InterfaceSerializer} to serialize and deserialize
 * objects that implement this interface.
 * </p>
 * <p>
 * Using this interface is intended to be a more convienient way to implement custom serialization
 * and deserialization without needing to register the serializer with the {@link Json} class.
 * </p>
 * @see InterfaceSerializer
 */
public interface CustomSerializer {
    public JsonValue<?> serialize();
    public void deserialize(JsonValue<?> json);
}
