package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Short}s
 * <p>
 * All numeric values are serialized as {@link JsonNumber}s.
 * </p>
 * @see Serializer
 * @see Short
 * @see JsonNumber
 */
public class ShortSerializer implements Serializer<Short> {

    /**
     * Serializes a {@link Short} object into a {@link JsonNumber}.
     * @param obj The {@link Short} object to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Short}.class.
     * @return A {@link JsonNumber} representing the {@link Short} object.
     */
    public JsonValue<?> serialize(Short obj, Class<? extends Short> clazz) {
        return new JsonNumber(obj);
    }

    /**
     * Deserializes a {@link JsonNumber} into a {@link Short}.
     * @param json The {@link JsonNumber} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Short}.class.
     * @return A {@link Short} representing the {@link JsonNumber}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonNumber}.
     */
    public Short deserialize(JsonValue<?> json, Class<? extends Short> clazz) throws JsonDeserializationException {
        if(json == null){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got null");
        }
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().shortValue();
        }
    }
    
}
