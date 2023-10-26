package ca.awoo.json;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import ca.awoo.json.parsers.JsonParser;
import ca.awoo.json.types.JsonNull;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.Parser.Match;
import ca.awoo.praser.character.CharacterStream;

/**
 * Test parsing of null in Json
 */
public class NullParserTest {
    @Test
    public void testNull() throws Exception {
        JsonParser parser = new JsonParser();
        Match<JsonValue<?>> match = parser.parse(new CharacterStream(new ByteArrayInputStream("null".getBytes())));
        assertTrue("Did match", match.isMatch());
        assertTrue("Matched on null", match.value instanceof JsonNull);
    }
}
