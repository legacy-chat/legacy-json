package ca.awoo.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.awoo.fwoabl.Optional;
import ca.awoo.json.types.JsonObject;
import ca.awoo.json.types.JsonValue;

public class OptionalTest {
    public static class WithOptional{
        public Optional<String> str;

        public WithOptional(Optional<String> str) {
            this.str = str;
        }

        public WithOptional() {
            this.str = Optional.none(String.class);
        }
    }

    @Test
    public void testSerializeSome() throws Exception {
        WithOptional with = new WithOptional(Optional.some("Hello"));
        Json json = new Json();
        json.defaultConfig();
        String serialized = json.toJson(with, WithOptional.class);
        System.out.println("testSerializeSome: " + serialized);
        WithOptional deserialized = json.fromJson(serialized, WithOptional.class);
        assertEquals("String is preserved", with.str, deserialized.str);
    }

    @Test
    public void testSerializeNone() throws Exception {
        WithOptional with = new WithOptional();
        Json json = new Json();
        json.defaultConfig();
        String serialized = json.toJson(with, WithOptional.class);
        System.out.println("testSerializeNone: " + serialized);
        WithOptional deserialized = json.fromJson(serialized, WithOptional.class);
        assertEquals("String is preserved", with.str, deserialized.str);
    }

    @Test
    public void testJsonValueSome() throws Exception {
        WithOptional with = new WithOptional(Optional.some("Hello"));
        Json json = new Json();
        json.defaultConfig();
        JsonValue<?> serialized = json.toJsonValue(with, WithOptional.class);
        assertTrue("Is object", serialized instanceof JsonObject);
        JsonObject object = (JsonObject) serialized;
        assertTrue("Object contains \"str\"", object.getValue().containsKey("str"));
        assertTrue("Object contains string", object.getValue().get("str") instanceof JsonValue);
        JsonValue<?> str = (JsonValue<?>) object.getValue().get("str");
        assertTrue("String is JsonString", str instanceof JsonValue);
        assertEquals("String is preserved", with.str.get(), str.getValue());
    }

    @Test
    public void testJsonValueNone() throws Exception {
        WithOptional with = new WithOptional();
        Json json = new Json();
        json.defaultConfig();
        JsonValue<?> serialized = json.toJsonValue(with, WithOptional.class);
        assertTrue("Is object", serialized instanceof JsonObject);
        JsonObject object = (JsonObject) serialized;
        assertFalse("Object does not contain \"str\"", object.getValue().containsKey("str"));
    }
    
}
