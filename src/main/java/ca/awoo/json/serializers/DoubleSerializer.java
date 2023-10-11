package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Double}s
 * <p>
 * All numeric values are serialized as {@link JsonNumber}s.
 * </p>
 * @see Serializer
 * @see Double
 * @see JsonNumber
 */
public class DoubleSerializer implements Serializer<Double> {

    /**
     * Serializes a {@link Double} object into a {@link JsonNumber}.
     * @param obj The {@link Double} object to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Double}.class.
     * @return A {@link JsonNumber} representing the {@link Double} object.
     */
    public JsonValue<?> serialize(Double obj, Class<? extends Double> clazz) {
        return new JsonNumber(obj);
    }

    /**
     * Deserializes a {@link JsonNumber} into a {@link Double}.
     * @param json The {@link JsonNumber} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Double}.class.
     * @return A {@link Double} representing the {@link JsonNumber}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonNumber}.
     */
    public Double deserialize(JsonValue<?> json, Class<? extends Double> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().doubleValue();
        }
    }
    
}
