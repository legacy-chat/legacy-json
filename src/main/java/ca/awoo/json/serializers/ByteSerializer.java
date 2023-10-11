package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Byte}s
 * <p>
 * All numeric values are serialized as {@link JsonNumber}s.
 * </p>
 * @see Serializer
 * @see Byte
 * @see JsonNumber
 */
public class ByteSerializer implements Serializer<Byte>{

    /**
     * Serializes a {@link Byte} object into a {@link JsonNumber}.
     * @param obj The {@link Byte} object to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Byte}.class.
     * @return A {@link JsonNumber} representing the {@link Byte} object.
     */
    public JsonValue<?> serialize(Byte obj, Class<? extends Byte> clazz) {
        return new JsonNumber(obj);
    }

    /**
     * Deserializes a {@link JsonNumber} into a {@link Byte}.
     * @param json The {@link JsonNumber} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Byte}.class.
     * @return A {@link Byte} representing the {@link JsonNumber}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonNumber}.
     */
    public Byte deserialize(JsonValue<?> json, Class<? extends Byte> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().byteValue();
        }
    }
    
}
