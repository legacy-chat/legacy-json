package ca.awoo.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import ca.awoo.json.parsers.JsonParser;
import ca.awoo.json.types.JsonObject;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.Parser.Match;
import ca.awoo.praser.character.CharacterStream;

/**
 * Test parsing an empty object.
 * @since 0.0.2
 * @see JsonParser
 */
public class EmptyObjectTest {

    /**
     * Tests that the parser can parse an empty object.
     * @throws Exception
     */
    @Test
    public void testEmptyObject() throws Exception {
        JsonParser parser = new JsonParser();
        Match<JsonValue<?>> match = parser.parse(new CharacterStream(new ByteArrayInputStream("{}".getBytes())));
        assertTrue("Did match", match.isMatch());
        assertTrue("Matched on object", match.value instanceof JsonObject);
        JsonObject object = (JsonObject) match.value;
        assertEquals("Object is empty", 0, object.getValue().size());
    }

    @Test
    public void testEmptyObjectWhitespace() throws Exception {
        JsonParser parser = new JsonParser();
        Match<JsonValue<?>> match = parser.parse(new CharacterStream(new ByteArrayInputStream("{  \n}".getBytes())));
        assertTrue("Did match", match.isMatch());
        assertTrue("Matched on object", match.value instanceof JsonObject);
        JsonObject object = (JsonObject) match.value;
        assertEquals("Object is empty", 0, object.getValue().size());
    }
}
