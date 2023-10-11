package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

public class DoubleSerializer implements Serializer<Double> {

    public JsonValue<?> serialize(Double obj, Class<? extends Double> clazz) throws JsonSerializationException {
        return new JsonNumber(obj);
    }

    public Double deserialize(JsonValue<?> json, Class<? extends Double> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().doubleValue();
        }
    }
    
}
