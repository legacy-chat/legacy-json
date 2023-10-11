package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

public class FloatSerializer implements Serializer<Float> {

    public JsonValue<?> serialize(Float obj, Class<? extends Float> clazz) throws JsonSerializationException {
        return new JsonNumber(obj);
    }

    public Float deserialize(JsonValue<?> json, Class<? extends Float> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().floatValue();
        }
    }
    
}
