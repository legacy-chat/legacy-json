package ca.awoo.json.parsers;

import ca.awoo.json.types.JsonNull;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.character.StringParser;

public class JsonNullParser extends Parser<Character, JsonValue<?>> {

    private static final StringParser parser = new StringParser("null");

    @Override
    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        Match<String> match = parser.parse(input, offset);
        if(match.isMatch()){
            return new Match<JsonValue<?>>(JsonNull.INSTANCE, match.length);
        }else {
            return new Match<JsonValue<?>>(null, 0);
        }
    }
    
}
