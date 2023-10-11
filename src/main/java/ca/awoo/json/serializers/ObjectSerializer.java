package ca.awoo.json.serializers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ca.awoo.json.Json;
import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonObject;
import ca.awoo.json.types.JsonValue;

public class ObjectSerializer implements Serializer<Object> {

    private Json json;

    public ObjectSerializer(Json json) {
        this.json = json;
    }

    public JsonValue<?> serialize(Object obj, Class<? extends Object> clazz) throws JsonSerializationException {
        JsonObject json = new JsonObject();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(field.isSynthetic() || Modifier.isTransient(field.getModifiers())){
                continue;
            }
            field.setAccessible(true);
            try {
                if(field.get(obj).equals(obj)){
                    throw new JsonSerializationException(obj, "Cannot serialize object with circular reference");
                }
                json.put(field.getName(), this.json.toJsonValue(field.get(obj), field.getType()));
            } catch (IllegalArgumentException e) {
                throw new JsonSerializationException(obj, "Error serializing field " + field.getName(), e);
            } catch (IllegalAccessException e) {
                throw new JsonSerializationException(obj, "Error serializing field " + field.getName(), e);
            }
        }
        return json;
    }

    public Object deserialize(JsonValue<?> json, Class<? extends Object> clazz) throws JsonDeserializationException {
        if(!(json instanceof JsonObject)){
            throw new JsonDeserializationException(json, "Expected JsonObject, got " + json.getClass().getSimpleName());
        }else{
            JsonObject obj = (JsonObject)json;
            try {
                Object instance = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for(Field field : fields){
                    if(field.isSynthetic() || Modifier.isTransient(field.getModifiers())){
                        continue;
                    }
                    field.setAccessible(true);
                    field.set(instance, this.json.fromJsonValue(obj.get(field.getName()), field.getType()));
                }
                return instance;
            } catch (InstantiationException e) {
                throw new JsonDeserializationException(json, "Error deserializing object", e);
            } catch (IllegalAccessException e) {
                throw new JsonDeserializationException(json, "Error deserializing object", e);
            }
        }
    }
    
}
