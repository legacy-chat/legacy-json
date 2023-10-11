package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Integer}s
 * <p>
 * All numeric values are serialized as {@link JsonNumber}s.
 * </p>
 * @see Serializer
 * @see Integer
 * @see JsonNumber
 */
public class IntegerSerializer implements Serializer<Integer> {

    /**
     * Serializes a {@link Integer} object into a {@link JsonNumber}.
     * @param obj The {@link Integer} object to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Integer}.class.
     * @return A {@link JsonNumber} representing the {@link Integer} object.
     */
    public JsonValue<?> serialize(Integer obj, Class<? extends Integer> clazz) {
        return new JsonNumber(obj);
    }

    /**
     * Deserializes a {@link JsonNumber} into a {@link Integer}.
     * @param json The {@link JsonNumber} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Integer}.class.
     * @return A {@link Integer} representing the {@link JsonNumber}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonNumber}.
     */
    public Integer deserialize(JsonValue<?> json, Class<? extends Integer> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().intValue();
        }
    }
    
}
