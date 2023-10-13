package ca.awoo.json;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import ca.awoo.json.parsers.JsonParser;
import ca.awoo.json.serializers.*;
import ca.awoo.json.types.*;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser.Match;
import ca.awoo.praser.character.CharacterStream;

/**
 * The main class for the JSON library. This class is used to serialize and deserialize objects to and from JSON.
 * <p>
 * To use the library, you create an instance of JSON and register any custom serializers you need. Then you can call toJson and fromJson to serialize and deserialize objects.
 * Remember to call defaultConfig() to register the default serializers for the primative types and strings if you need them.
 * </p>
 * <p>
 * Internally the library uses an intermediate format called JsonValue. This is a tree structure that represents the JSON.
 * The JsonValue class has a toString method that will convert the JsonValue to a JSON string.
 * The JsonValue system allows us to parse JSON strings before we have a schema, and then convert the JsonValue to an object once we have a schema.
 * This is usefull if the type of a field in the JSON is determined by another field in the JSON.
 * </p>
 * <p>
 * The library comes with serializers for the primative types and strings. It also comes with a serializer for objects that uses reflection to serialize and deserialize the fields of the object.
 * All fields are serialized including private fields excepting synthetic and transient fields.
 * If you wish a field to be left out of the serialization, mark it as transient.
 * If you can't mark it as transient, you can register a custom serializer.
 * You should register a custom serializer for any class that has special requirements for how it's serialized.
 * </p>
 * <p>
 * During deserialization, an object is constructed using the default constructor and then the fields are set using reflection.
 * If you need to do something special during deserialization, you can register a custom serializer.
 * If you're class doesn't have a no-arg constructor, you will need to register a custom serializer for it.
 * </p>
 * <p>
 * The library is not thread safe. If you need to use it in a multithreaded environment, you will need to synchronize access to the JSON object.
 * </p>
 * <p>
 * The library is not yet complete. It does not yet support parsing JSON strings. It also does not yet support serializing and deserializing arrays or collections.
 * </p>
 * <p>
 * Usage examples:
 * </p>
 * <pre>
 * Json json = new Json();
 * json.defaultConfig();
 * String jsonStr = json.toJson(new MyClass(), MyClass.class);
 * MyClass obj = json.fromJson(jsonStr, MyClass.class);
 * </pre>
 */
public class Json {
    private Set<Pair<ClassMatcher, Serializer<?>>> serializers = new HashSet<Pair<ClassMatcher, Serializer<?>>>();
    private Serializer<?> defaultSerializer = null;

    private JsonParser parser = new JsonParser();

    private Pair<ClassMatcher, Serializer<?>> getSerializerPair(Class<?> clazz) {
        clazz = mikeTyson(clazz);
        for (Pair<ClassMatcher, Serializer<?>> pair : serializers) {
            if (pair.first.matches(clazz)) {
                return pair;
            }
        }
        return null;
    }

    private Pair<ClassMatcher, Serializer<?>> getSerializerPair(ClassMatcher matcher){
        for (Pair<ClassMatcher, Serializer<?>> pair : serializers) {
            if (pair.first.equals(matcher)) {
                return pair;
            }
        }
        return null;
    }

    /**
     * Registers a serializer for a specific class. This serializer will be used to serialize and deserialize objects of the specified class. If no serializer is registered for a class, the default serializer will be used.
     * 
     * Generally you will only register a serializer for a class if the class has odd requirements for how it's serialized. For example, if you have a class that represents a unique id that gets serialized as a string in JSON rather than an object, you would register a serializer for that class that converts the unique id to a string and back.
     * @param <T> The type of the class to register the serializer for
     * @param clazz The class to register the serializer for
     * @param serializer The serializer to use for the specified class
     */
    public <T> void registerSerializer(Class<T> clazz, Serializer<T> serializer) {
        if(getSerializerPair(clazz) != null){
            throw new RuntimeException("Serializer already registered for " + clazz.getName());
        }
        serializers.add(new Pair<ClassMatcher, Serializer<?>>(new SingleClassMatcher(clazz), serializer));
    }

    /**
     * Registers a serializer for a specific class matcher.
     * The class matcher allows for more complex matching of classes than just a single class.
     * For example, you could register a serializer for all classes that implement a specific interface.
     * This is used internally to register the serializer for arrays.
     * @see ClassMatcher
     * @param <T> The type of the class to register the serializer for
     * @param matcher The class matcher to register the serializer for
     * @param serializer The serializer to use for the specified class matcher
     */
    public <T> void registerSerializer(ClassMatcher matcher, Serializer<T> serializer) {
        if(getSerializerPair(matcher) != null){
            throw new RuntimeException("Serializer already registered for " + matcher);
        }
        serializers.add(new Pair<ClassMatcher, Serializer<?>>(matcher, serializer));
    }

    /**
     * Registers a serializer to be used as the default serializer. This serializer will be used to serialize and deserialize objects of any class that does not have a specific serializer registered for it.
     * @param serializer The serializer to use as the default serializer
     */
    public void registerDefaultSerializer(Serializer<?> serializer) {
        defaultSerializer = serializer;
    }

    /**
     * Gets the serializer registered for the specified class. If no serializer is registered for the specified class, the default serializer will be returned. Used internally to retrieve the serializer to be used during serialization and deserialization.
     * @param <T> The type of the class to get the serializer for
     * @param clazz The class to get the serializer for
     * @return The serializer registered for the specified class, or the default serializer if no serializer is registered for the specified class
     */
    @SuppressWarnings("unchecked")
    public <T> Serializer<T> getSerializer(Class<T> clazz) {
        Pair<ClassMatcher, Serializer<?>> pair = getSerializerPair(clazz);
        if (pair != null) {
            return (Serializer<T>) pair.second;
        } else {
            return (Serializer<T>) defaultSerializer;
        }
    }

    /**
     * Registers the default serializers for the primative types and strings. This is not called automatically in case you want to use your own serializers for the primative types.
     */
    public void defaultConfig(){
        registerDefaultSerializer(new ObjectSerializer(this));
        registerSerializer(String.class, new StringSerializer());
        registerSerializer(Integer.class, new IntegerSerializer());
        registerSerializer(Long.class, new LongSerializer());
        registerSerializer(Float.class, new FloatSerializer());
        registerSerializer(Double.class, new DoubleSerializer());
        registerSerializer(Boolean.class, new BooleanSerializer());
        registerSerializer(Character.class, new CharacterSerializer());
        registerSerializer(Byte.class, new ByteSerializer());
        registerSerializer(Short.class, new ShortSerializer());
        registerSerializer(new ArrayClassMatcher(), new ArraySerializer(this));
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

    /**
     * Serializes an object to a JsonValue, our intermediate format. This is used internally by toJson and fromJson, but may be used externally if you like.
     * @param <T> The type of the object to serialize
     * @param obj The object to serialize
     * @param clazz The class of the object to serialize
     * @return The JsonValue representing the serialized object
     * @throws JsonSerializationException If there is an error while converting the object to a JsonValue
     */
    @SuppressWarnings("unchecked")
    public <T> JsonValue<?> toJsonValue(T obj, Class<? extends Object> clazz) throws JsonSerializationException{
        if(clazz.isPrimitive()){
            clazz = (Class<? extends Object>) mikeTyson(clazz);
        }
        Serializer<T> serializer = (Serializer<T>) getSerializer(clazz);
        if (serializer == null) {
            throw new RuntimeException("No serializer found for " + clazz.getName());
        }
        //TODO: this is where the logging goes!
        //System.out.println("Serializing " + obj.getClass().getName() + " with " + serializer.getClass().getName());
        return serializer.serialize(obj, (Class<? extends T>) clazz);
    }

    /**
     * Deserializes a JsonValue to an object. This is used internally by toJson and fromJson, but may be used externally if you like.
     * @param <T> The type of the object to deserialize
     * @param json The JsonValue to deserialize
     * @param clazz The class of the object to deserialize
     * @return The deserialized object
     * @throws JsonDeserializationException If there is an error while converting the JsonValue to an object
     */
    public <T> T fromJsonValue(JsonValue<?> json, Class<T> clazz) throws JsonDeserializationException{
        Serializer<T> serializer = getSerializer(clazz);
        if (serializer == null) {
            throw new RuntimeException("No serializer found for " + clazz.getName());
        }
        T obj =  serializer.deserialize(json, clazz);
        return obj;
    }

    /**
     * Serializes an object to a JSON string.
     * @param <T> The type of the object to serialize
     * @param obj The object to serialize
     * @param clazz The class of the object to serialize
     * @return The JSON string representing the serialized object
     * @throws JsonSerializationException If there is an error while converting the object to a JsonValue internally
     */
    public <T> String toJson(T obj, Class<T> clazz) throws JsonSerializationException{
        return toJsonValue(obj, clazz).toString();
    }

    /**
     * Deserializes a JSON string to an object.
     * @param <T> The type of the object to deserialize
     * @param json The JSON string to deserialize
     * @param clazz The class of the object to deserialize
     * @return The deserialized object
     * @throws JsonDeserializationException If there is an error while converting the JsonValue used internally to an object
     */
    public <T> T fromJson(String json, Class<T> clazz) throws JsonDeserializationException{
        Match<JsonValue<?>> match;
        try {
            match = parser.parse(new CharacterStream(new ByteArrayInputStream(json.getBytes("UTF-8"))));
            if(!match.isMatch()){
                throw new JsonDeserializationException(null, "Could not parse JSON: Not valid Json: " + json);
            }
            return fromJsonValue(match.value, clazz);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new JsonDeserializationException(null, "Could not parse JSON: Parser threw exception: " + json, e);
        }
        
    }

}
