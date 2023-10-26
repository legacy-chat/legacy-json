package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonBoolean;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Boolean}s
 * @see Serializer
 * @see Boolean
 * @see JsonBoolean
 */
public class BooleanSerializer implements Serializer<Boolean> {

    /**
     * Serializes a {@link Boolean} into a {@link JsonBoolean}.
     * @param obj The {@link Boolean} to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Boolean}.class.
     * @return the serialized {@link JsonBoolean}.
     */
    public JsonValue<?> serialize(Boolean obj, Class<? extends Boolean> clazz) {
        return new JsonBoolean(obj);
    }

    /**
     * Deserializes a {@link JsonBoolean} into a {@link Boolean}.
     * @param json The {@link JsonBoolean} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Boolean}.class.
     * @return the deserialized {@link Boolean}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonBoolean}.
     */
    public Boolean deserialize(JsonValue<?> json, Class<? extends Boolean> clazz) throws JsonDeserializationException {
        if(json == null){
            throw new JsonDeserializationException(json, "Expected JsonBoolean, got null");
        }
        if(!(json instanceof JsonBoolean)){
            throw new JsonDeserializationException(json, "Expected JsonBoolean, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonBoolean)json).getValue();
        }
    }
    
}
