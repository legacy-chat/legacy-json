package ca.awoo.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import ca.awoo.json.parsers.JsonParser;
import ca.awoo.json.types.JsonObject;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.Parser.Match;
import ca.awoo.praser.character.CharacterStream;

/**
 * Tests for {@link JsonParser}.
 */
public class JsonParserTest{
    /**
     * Tests that the parser can parse a string.
     * <p>
     * Test string: <code>"hello"</code>
     * </p>
     * @throws Exception if an error occurs
     */
    @Test
    public void testString() throws Exception{
        Match<JsonValue<?>> match = new JsonParser().parse(new CharacterStream(new ByteArrayInputStream("\"hello\"".getBytes())));
        assertTrue("Matched on string", match.isMatch());
        System.out.println(match.value);
    }

    /**
     * Tests that the parser can parse a number.
     * <p>
     * Test string: <code>76</code>
     * </p>
     * @throws Exception if an error occurs
     */
    @Test
    public void testNumber() throws Exception{
        Match<JsonValue<?>> match = new JsonParser().parse(new CharacterStream(new ByteArrayInputStream("76".getBytes())));
        assertTrue("Matched on number", match.isMatch());
        System.out.println(match.value);
    }

    /**
     * Tests that the parser can parse a boolean.
     * <p>
     * Test string: <code>true</code>
     * </p>
     * @throws Exception if an error occurs
     */
    @Test
    public void testBoolean() throws Exception{
        Match<JsonValue<?>> match = new JsonParser().parse(new CharacterStream(new ByteArrayInputStream("true".getBytes())));
        assertTrue("Matched on boolean", match.isMatch());
        System.out.println(match.value);
    }

    /**
     * Tests that the parser can parse an array.
     * <p>
     * Test string: <code>[1,2,3]</code>
     * </p>
     * @throws Exception if an error occurs
     */
    @Test
    public void testArray() throws Exception{
        Match<JsonValue<?>> match = new JsonParser().parse(new CharacterStream(new ByteArrayInputStream("[1,2,3]".getBytes())));
        assertTrue("Matched on array", match.isMatch());
        System.out.println(match.value);

        match = new JsonParser().parse(new CharacterStream(new ByteArrayInputStream("[1.0, 2.0, 3.0]".getBytes())));
        assertTrue("Matched on array", match.isMatch());
        System.out.println(match.value);
    }

    /**
     * Tests that the parser can parse an object.
     * <p>
     * Test string: <code>{"hello": "world"}</code>
     * </p>
     * @throws Exception if an error occurs
     */
    @Test
    public void testObject() throws Exception{
        Match<JsonValue<?>> match = new JsonParser().parse(new CharacterStream(new ByteArrayInputStream("{\"hello\": \"world\"}".getBytes())));
        assertTrue("Matched on object", match.isMatch());
        System.out.println(match.value);
    }

    /**
     * Tests that the parser can parse an empty array.
     * <p>
     * Test string: <code>{"attachments": []}</code>
     * </p>
     * @throws Exception if an error occurs
     * @since 0.0.2
     */
    @Test
    public void testEmptyArray() throws Exception {
        JsonParser parser = new JsonParser();
        Match<JsonValue<?>> match = parser.parse(new CharacterStream(new ByteArrayInputStream("{\"attachments\": []}".getBytes())));
        assertTrue("Did match", match.isMatch());
        assertTrue("Matched on object", match.value instanceof JsonObject);
    }

    /**
     * Tests that the parser cannot parse an array with bogus in it.
     * <p>
     * Test string: <code>{"attachments": [bogus]}</code>
     * </p>
     * @throws Exception
     */
    @Test
    public void testBogusArray() throws Exception {
        JsonParser parser = new JsonParser();
        Match<JsonValue<?>> match = parser.parse(new CharacterStream(new ByteArrayInputStream("{\"attachments\": [bogus]}".getBytes())));
        assertFalse("Did not match", match.isMatch());
    }

    /**
     * Tests that the parser can parse an empty object.
     * <p>
     * Test string: <code>{}</code>
     * </p>
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

    /**
     * Tests that the parser can parse an empty object with whitespace.
     * <p>
     * Test string: <code>{  \n}</code>
     * </p>
     * @throws Exception
     */
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
