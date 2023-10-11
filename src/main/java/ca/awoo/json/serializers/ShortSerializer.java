package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

public class ShortSerializer implements Serializer<Short> {

    public JsonValue<?> serialize(Short obj, Class<? extends Short> clazz) throws JsonSerializationException {
        return new JsonNumber(obj);
    }

    public Short deserialize(JsonValue<?> json, Class<? extends Short> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().shortValue();
        }
    }
    
}
