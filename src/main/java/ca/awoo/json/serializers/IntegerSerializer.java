package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

public class IntegerSerializer implements Serializer<Integer> {

    public JsonValue<?> serialize(Integer obj, Class<? extends Integer> clazz) throws JsonSerializationException {
        return new JsonNumber(obj);
    }

    public Integer deserialize(JsonValue<?> json, Class<? extends Integer> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().intValue();
        }
    }
    
}
