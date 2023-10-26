package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonString;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link String}s
 * @see Serializer
 * @see String
 * @see JsonString
 */
public class StringSerializer implements Serializer<String> {

    /**
     * Serializes a {@link String} into a {@link JsonString}.
     * @param obj The {@link String} to serialize.
     * @param clazz The class of the object to serialize. Should be {@link String}.class.
     * @return the serialized {@link JsonString}.
     */
    public JsonValue<String> serialize(String obj, Class<? extends String> clazz) {
        return new JsonString(obj);
    }

    /**
     * Deserializes a {@link JsonString} into a {@link String}.
     * @param json The {@link JsonString} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link String}.class.
     * @return the deserialized {@link String}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonString}.
     */
    public String deserialize(JsonValue<?> json, Class<? extends String> clazz) throws JsonDeserializationException {
        if(json == null){
            throw new JsonDeserializationException(json, "Expected JsonString, got null");
        }
        if(!(json instanceof JsonString)){
            throw new JsonDeserializationException(json, "Expected JsonString, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonString)json).getValue();
        }
    }
    
}
