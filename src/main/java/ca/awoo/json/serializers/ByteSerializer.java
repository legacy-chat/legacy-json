package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;

public class ByteSerializer implements Serializer<Byte>{

    public JsonValue<?> serialize(Byte obj, Class<? extends Byte> clazz) throws JsonSerializationException {
        return new JsonNumber(obj);
    }

    public Byte deserialize(JsonValue<?> json, Class<? extends Byte> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonNumber)){
            throw new JsonDeserializationException(json, "Expected JsonNumber, got " + json.getClass().getSimpleName());
        }else{
            return ((JsonNumber)json).getValue().byteValue();
        }
    }
    
}
