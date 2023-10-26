package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonString;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes {@link Character}s
 * <p>
 * Characters are serialized as JSON strings.
 * </p>
 * @see Serializer
 * @see Character
 * @see JsonString
 */
public class CharacterSerializer implements Serializer<Character>{

    /**
     * Serializes a {@link Character} into a {@link JsonString}.
     * @param obj The {@link Character} to serialize.
     * @param clazz The class of the object to serialize. Should be {@link Character}.class.
     * @return the serialized {@link JsonString}.
     */
    public JsonValue<?> serialize(Character obj, Class<? extends Character> clazz) {
        return new JsonString(obj.toString());
    }

    /**
     * Deserializes a {@link JsonString} into a {@link Character}.
     * @param json The {@link JsonString} to deserialize.
     * @param clazz The class of the object to deserialize. Should be {@link Character}.class.
     * @return the deserialized {@link Character}.
     * @throws JsonDeserializationException if the {@link JsonValue} is not a {@link JsonString}.
     */
    public Character deserialize(JsonValue<?> json, Class<? extends Character> clazz)
            throws JsonDeserializationException {
        if(json == null){
            throw new JsonDeserializationException(json, "Expected JsonString, got null");
        }
        if(!(json instanceof JsonString)){
            throw new JsonDeserializationException(json, "Expected JsonString, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonString)json).getValue().charAt(0);
        }
    }
    
}
