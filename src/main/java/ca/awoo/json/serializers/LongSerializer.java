package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

public class LongSerializer implements Serializer<Long> {

    public JsonValue<?> serialize(Long obj, Class<? extends Long> clazz) throws JsonSerializationException {
        return new JsonNumber(obj);
    }

    public Long deserialize(JsonValue<?> json, Class<? extends Long> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().longValue();
        }
    }
    
}
