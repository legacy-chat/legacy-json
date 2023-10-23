package ca.awoo.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.awoo.json.types.JsonString;
import ca.awoo.json.types.JsonValue;

/**
 * Tests for custom serialization using the CustomSerializer interface.
 */
public class CustomSerializerTest {

    /**
     * A custom serializer that serializes a long to a string.
     */
    public static class CustomSerializerImpl implements CustomSerializer {

        private long value;

        public JsonValue<?> serialize() {
            return new JsonString(Long.toString(value));
        }

        public void deserialize(JsonValue<?> json) {
            value = Long.parseLong(((JsonString)json).getValue());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (value ^ (value >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            CustomSerializerImpl other = (CustomSerializerImpl) obj;
            if (value != other.value)
                return false;
            return true;
        }

        
    }

    @Test
    public void testCustomSerializer() throws Exception {
        Json json = new Json();
        json.defaultConfig();

        CustomSerializerImpl obj = new CustomSerializerImpl();
        obj.value = 1234567890L;

        JsonValue<?> serialized = json.toJsonValue(obj, CustomSerializerImpl.class);
        assertTrue("serialized instanceof JsonString", serialized instanceof JsonString);

        CustomSerializerImpl deserialized = json.fromJsonValue(serialized, CustomSerializerImpl.class);
        assertEquals("Deserialized value equals serialized", obj.value, deserialized.value);
    }
}
