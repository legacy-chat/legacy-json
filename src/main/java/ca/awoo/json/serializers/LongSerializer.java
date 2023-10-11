package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Long}s
 * <p>
 * All numeric values are serialized as {@link JsonNumber}s.
 * </p>
 * @see Serializer
 * @see Long
 * @see JsonNumber
 */
public class LongSerializer implements Serializer<Long> {

    /**
     * Serializes a {@link Long} object into a {@link JsonNumber}.
     * @param obj The {@link Long} object to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Long}.class.
     * @return A {@link JsonNumber} representing the {@link Long} object.
     */
    public JsonValue<?> serialize(Long obj, Class<? extends Long> clazz) {
        return new JsonNumber(obj);
    }

    /**
     * Deserializes a {@link JsonNumber} into a {@link Long}.
     * @param json The {@link JsonNumber} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Long}.class.
     * @return A {@link Long} representing the {@link JsonNumber}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonNumber}.
     */
    public Long deserialize(JsonValue<?> json, Class<? extends Long> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().longValue();
        }
    }
    
}
