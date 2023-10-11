package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonString;
import ca.awoo.json.types.JsonValue;

public class CharacterSerializer implements Serializer<Character>{

    public JsonValue<?> serialize(Character obj, Class<? extends Character> clazz) throws JsonSerializationException {
        return new JsonString(obj.toString());
    }

    public Character deserialize(JsonValue<?> json, Class<? extends Character> clazz)
            throws JsonDeserializationException {
        if(!(json instanceof JsonString)){
            throw new JsonDeserializationException(json, "Expected JsonString, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonString)json).getValue().charAt(0);
        }
    }
    
}
