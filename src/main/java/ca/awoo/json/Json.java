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
        registerDefaultSerializer(new ObjectSerializer(this));
        registerSerializer(String.class, new StringSerializer());
        registerSerializer(Boolean.class, new BooleanSerializer());
    }

    /**
     * Converts a primative class to its object equivalent. Mike Tyson is a boxer.
     * @param primative The primative class to convert
     * @return The object equivalent of the primative class
     */
    private Class<?> mikeTyson(Class<?> primative){
        if(primative == int.class){
            return Integer.class;
        }else if(primative == long.class){
            return Long.class;
        }else if(primative == float.class){
            return Float.class;
        }else if(primative == double.class){
            return Double.class;
        }else if(primative == boolean.class){
            return Boolean.class;
        }else if(primative == char.class){
            return Character.class;
        }else if(primative == byte.class){
            return Byte.class;
        }else if(primative == short.class){
            return Short.class;
        }else{
            return primative;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> JsonValue<?> toJsonValue(T obj, Class<? extends Object> clazz) throws JsonSerializationException{
        if(clazz.isPrimitive()){
            clazz = (Class<? extends Object>) mikeTyson(clazz);
        }
        Serializer<T> serializer = (Serializer<T>) getSerializer(clazz);
        if (serializer == null) {
            throw new RuntimeException("No serializer found for " + clazz.getName());
        }
        System.out.println("Serializing " + obj.getClass().getName() + " with " + serializer.getClass().getName());
        return serializer.serialize(obj, (Class<? extends T>) clazz);
    }

    public <T> T fromJsonValue(JsonValue<?> json, Class<T> clazz) throws JsonDeserializationException{
        Serializer<T> serializer = getSerializer(clazz);
        if (serializer == null) {
            throw new RuntimeException("No serializer found for " + clazz.getName());
        }
        return serializer.deserialize(json, clazz);
    }

    public <T> String toJson(T obj, Class<T> clazz) throws JsonSerializationException{
        return toJsonValue(obj, clazz).toString();
    }

    public <T> T fromJson(String json, Class<T> clazz) throws JsonDeserializationException{
        throw new UnsupportedOperationException("Json parsing not yet implemented");
        //return fromJsonValue(JsonValue.parse(json), clazz);
    }

}
