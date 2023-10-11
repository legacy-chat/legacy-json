package ca.awoo.json.serializers;

import java.lang.reflect.Array;

import ca.awoo.json.Json;
import ca.awoo.json.JsonDeserializationException;
import ca.awoo.json.JsonSerializationException;
import ca.awoo.json.Serializer;
import ca.awoo.json.types.JsonArray;
import ca.awoo.json.types.JsonValue;

/**
 * Serializes and deserializes arrays
 * <p>
 * While JSON supports heterogeneous arrays, Java does not. Therefore, this serializer will only work with homogeneous arrays.
 * </p>
 * @see Serializer
 * @see Object[]
 * @see JsonArray
 */
public class ArraySerializer implements Serializer<Object>{

    private final Json json;

    public ArraySerializer(Json json) {
        this.json = json;
    }

    private Object[] getArray(Object val){
        if (val instanceof Object[])
        return (Object[])val;
        int arrlength = Array.getLength(val);
        Object[] outputArray = new Object[arrlength];
        for(int i = 0; i < arrlength; ++i){
        outputArray[i] = Array.get(val, i);
        }
        return outputArray;
    }

    public JsonValue<?> serialize(Object obj, Class<? extends Object> clazz) throws JsonSerializationException {
        JsonArray array = new JsonArray();
        Object[] arr = getArray(obj);
        for(Object o : arr){
            array.add(json.toJsonValue(o, clazz.getComponentType()));
        }
        return array;
    }

    public Object deserialize(JsonValue<?> json, Class<? extends Object> clazz)
            throws JsonDeserializationException {
        if(!(json instanceof JsonArray)){
            throw new JsonDeserializationException(json, "Expected JsonArray, got " + json.getClass().getSimpleName());
        }else{
            JsonArray array = (JsonArray)json;
            Object[] obj = new Object[array.getValue().size()];
            for(int i = 0; i < array.getValue().size(); i++){
                obj[i] = this.json.fromJsonValue(array.getValue().get(i), clazz.getComponentType());
            }
            return obj;
        }
    }
    
}
