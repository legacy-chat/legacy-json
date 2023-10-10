package ca.awoo.json;

import java.util.HashMap;
import java.util.Map;

import ca.awoo.json.serializers.*;
import ca.awoo.json.types.*;

public class Json {
    private Map<Class<?>, Serializer<?>> serializers = new HashMap<Class<?>, Serializer<?>>();
    private Serializer<?> defaultSerializer = null;

    public <T> void registerSerializer(Class<T> clazz, Serializer<T> serializer) {
        serializers.put(clazz, serializer);
    }

    public <T> void registerDefaultSerializer(Serializer<T> serializer) {
        defaultSerializer = serializer;
    }

    @SuppressWarnings("unchecked")
    public <T> Serializer<T> getSerializer(Class<T> clazz) {
        if (serializers.containsKey(clazz)) {
            return (Serializer<T>) serializers.get(clazz);
        } else {
            return (Serializer<T>) defaultSerializer;
        }
    }

    public void defaultConfig(){
        registerSerializer(String.class, new StringSerializer());
    }

    private <T> JsonValue<?> toJsonValue(T obj, Class<T> clazz){
        Serializer<T> serializer = getSerializer(clazz);
        if (serializer == null) {
            throw new RuntimeException("No serializer found for " + clazz.getName());
        }
        return serializer.serialize(obj);
    }

    private <T> T fromJsonValue(JsonValue<?> json, Class<T> clazz) throws JsonDeserializationException{
        Serializer<T> serializer = getSerializer(clazz);
        if (serializer == null) {
            throw new RuntimeException("No serializer found for " + clazz.getName());
        }
        return serializer.deserialize(json);
    }

    public <T> String toJson(T obj, Class<T> clazz){
        return toJsonValue(obj, clazz).toString();
    }

    public <T> T fromJson(String json, Class<T> clazz) throws JsonDeserializationException{
        throw new UnsupportedOperationException("Json parsing not yet implemented");
        //return fromJsonValue(JsonValue.parse(json), clazz);
    }

}
