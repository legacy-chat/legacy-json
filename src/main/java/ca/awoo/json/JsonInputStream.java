package ca.awoo.json;

import ca.awoo.json.parsers.JsonParser;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParsedStream;

/**
 * A stream of {@link JsonValue} objects.
 */
public class JsonInputStream extends ParsedStream<Character, JsonValue<?>> {

    public JsonInputStream(InputStreamOf<Character> input) {
        super(new JsonParser(), input);
    }
    
}
