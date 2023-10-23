package ca.awoo.json.serializers;

import ca.awoo.json.CustomSerializer;
import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonValue;

public class InterfaceSerializer implements Serializer<CustomSerializer> {

    public JsonValue<?> serialize(CustomSerializer obj, Class<? extends CustomSerializer> clazz)
            throws JsonSerializationException {
        return obj.serialize();
    }

    public CustomSerializer deserialize(JsonValue<?> json, Class<? extends CustomSerializer> clazz)
            throws JsonDeserializationException {
        CustomSerializer obj;
        try {
            obj = clazz.newInstance();
            obj.deserialize(json);
            return obj;
        } catch (InstantiationException e) {
            throw new JsonDeserializationException(json, "Could not instantiate " + clazz.getSimpleName());
        } catch (IllegalAccessException e) {
            throw new JsonDeserializationException(json, "Could not instantiate " + clazz.getSimpleName());
        }
    }
    
}
