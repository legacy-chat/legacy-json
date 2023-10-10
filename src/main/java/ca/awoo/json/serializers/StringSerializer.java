package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonString;
import ca.awoo.json.types.JsonValue;

public class StringSerializer implements Serializer<String> {

    public JsonValue<String> serialize(String obj) {
        return new JsonString(obj);
    }

    public String deserialize(JsonValue<?> json) throws JsonDeserializationException {
        if(!(json instanceof JsonString)){
            throw new JsonDeserializationException(json, "Expected JsonString, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonString)json).getValue();
        }
    }
    
}
