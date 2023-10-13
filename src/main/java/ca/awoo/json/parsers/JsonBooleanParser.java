package ca.awoo.json.parsers;

import ca.awoo.json.types.JsonBoolean;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.character.StringParser;
import ca.awoo.praser.parsers.OrParser;

/**
 * A parser for {@link JsonBoolean} objects.
 */
public class JsonBooleanParser extends Parser<Character, JsonValue<?>> {

    private final Parser<Character, String> parser;

    @SuppressWarnings("unchecked")
    public JsonBooleanParser(){
        this.parser = new OrParser<Character,String>(
            new StringParser("true"),
            new StringParser("false")
        );
    }

    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        Match<String> match = parser.parse(input, offset);
        if(match.isMatch()){
            Boolean value = match.value.equals("true");
            return new Match<JsonValue<?>>(new JsonBoolean(value), match.length);
        }
        return new Match<JsonValue<?>>(null, 0);
    }
    
}
