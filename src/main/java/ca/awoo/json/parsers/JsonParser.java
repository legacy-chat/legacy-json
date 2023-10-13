package ca.awoo.json.parsers;

import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.character.WhitespaceParser;
import ca.awoo.praser.parsers.OrParser;

public class JsonParser extends Parser<Character, JsonValue<?>> {

    @SuppressWarnings("unchecked")
    private final Parser<Character, JsonValue<?>> parser = new OrParser<Character, JsonValue<?>>(
        new JsonStringParser(),
        new JsonBooleanParser(),
        new JsonNumberParser(),
        new JsonObjectParser(),
        new JsonArrayParser()
    );

    private final Parser<Character, String> whitespaceParser = new WhitespaceParser();

    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        Match<String> whitespaceMatch = whitespaceParser.parse(input, offset);
        Match<JsonValue<?>> valueMatch = parser.parse(input, offset + whitespaceMatch.length);
        Match<String> whitespaceMatch2 = whitespaceParser.parse(input, offset + whitespaceMatch.length + valueMatch.length);
        if(valueMatch.isMatch()){
            return new Match<JsonValue<?>>(valueMatch.value, whitespaceMatch.length + valueMatch.length + whitespaceMatch2.length);
        }
        return new Match<JsonValue<?>>(null, 0);
    }
    
}
