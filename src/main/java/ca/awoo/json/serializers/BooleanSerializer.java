package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonBoolean;
import ca.awoo.json.types.JsonValue;

public class BooleanSerializer implements Serializer<Boolean> {

    public JsonValue<?> serialize(Boolean obj, Class<? extends Boolean> clazz) {
        return new JsonBoolean(obj);
    }

    public Boolean deserialize(JsonValue<?> json, Class<? extends Boolean> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonBoolean)){
            throw new JsonDeserializationException(json, "Expected JsonBoolean, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonBoolean)json).getValue();
        }
    }
    
}
