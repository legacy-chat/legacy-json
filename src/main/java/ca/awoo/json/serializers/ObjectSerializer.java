package ca.awoo.json.serializers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ca.awoo.json.Json;
import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonObject;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes objects.
 * <p>
 * This serializer uses reflection to serialize and deserialize objects.
 * It will serialize all fields of the object, including private and protected fields.
 * It will not serialize transient or synthetic fields.
 * </p>
 * @see Serializer
 * @see JsonObject
 */
public class ObjectSerializer implements Serializer<Object> {

    private Json json;

    /**
     * Creates a new ObjectSerializer with the given {@link Json} instance.
     * The serializer needs a reference to the containing {@link Json} instance to serialize and deserialize other objects within the object.
     * @see Json
     * @param json The {@link Json} instance to use.
     */
    public ObjectSerializer(Json json) {
        this.json = json;
    }

    /**
     * Serializes an object into a {@link JsonObject}.
     * @param obj The object to serialize.
     * @param clazz The class of the object to serialize.
     * @return A {@link JsonObject} representing the object.
     * @throws JsonSerializationException if the object could not be serialized.
     */
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

    /**
     * Deserializes a {@link JsonObject} into an object.
     * @param json The {@link JsonObject} to deserialize.
     * @param clazz The class of the object to deserialize.
     * @return An object representing the {@link JsonObject}.
     * @throws JsonDeserializationException if the {@link JsonValue} could not be deserialized.
     */
    public Object deserialize(JsonValue<?> json, Class<? extends Object> clazz) throws JsonDeserializationException {
        if(json == null){
            throw new JsonDeserializationException(json, "Expected JsonObject, got null");
        }
        if(!(json instanceof JsonObject)){
            throw new JsonDeserializationException(json, "Expected JsonObject, got " + json.getClass().getSimpleName());
        }else{
            JsonObject obj = (JsonObject)json;
            try {
                Object instance = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                //Iterate over all fields in the class
                for(Field field : fields){
                    //Skip synthetic and transient fields
                    if(field.isSynthetic() || Modifier.isTransient(field.getModifiers())){
                        continue;
                    }
                    //Make the field accessible
                    field.setAccessible(true);
                    try{
                        //Set the field to the deserialized value
                        field.set(instance, this.json.fromJsonValue(obj.get(field.getName()), field.getType()));
                    }catch(JsonDeserializationException e){
                        throw new JsonDeserializationException(json, "Error deserializing field " + field.getName() + " in class " + clazz.getName(), e);
                    }
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
