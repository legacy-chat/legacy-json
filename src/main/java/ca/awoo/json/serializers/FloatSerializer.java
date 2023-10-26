package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Float}s.
 * <p>
 * All numeric values are serialized as {@link JsonNumber}s.
 * </p>
 * @see Serializer
 * @see Float
 * @see JsonNumber
 */
public class FloatSerializer implements Serializer<Float> {

    /**
     * Serializes a {@link Float} object into a {@link JsonNumber}.
     * @param obj The {@link Float} object to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Float}.class.
     * @return A {@link JsonNumber} representing the {@link Float} object.
     */
    public JsonValue<?> serialize(Float obj, Class<? extends Float> clazz) {
        return new JsonNumber(obj);
    }

    /**
     * Deserializes a {@link JsonNumber} into a {@link Float}.
     * @param json The {@link JsonNumber} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Float}.class.
     * @return A {@link Float} representing the {@link JsonNumber}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonNumber}.
     */
    public Float deserialize(JsonValue<?> json, Class<? extends Float> clazz) throws JsonDeserializationException {
        if(json == null){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got null");
        }
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().floatValue();
        }
    }
    
}
