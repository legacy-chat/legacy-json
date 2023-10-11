package ca.awoo.json.serializers;

import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonValue;

public class BooleanSerializer implements Serializer<Boolean> {

    public JsonValue<?> serialize(Boolean obj, Class<? extends Boolean> clazz) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }

    public Boolean deserialize(JsonValue<?> json, Class<? extends Boolean> clazz) throws JsonDeserializationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }
    
}
