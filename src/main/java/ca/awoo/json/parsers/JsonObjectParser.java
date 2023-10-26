package ca.awoo.json.parsers;

import ca.awoo.json.types.JsonObject;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.character.SingleCharacterParser;
import ca.awoo.praser.character.WhitespaceParser;

/**
 * A parser for {@link JsonObject} objects.
 */
public class JsonObjectParser extends Parser<Character, JsonValue<?>> {

    private final Parser<Character, Character> openBrace = new SingleCharacterParser('{');
    private final Parser<Character, Character> closeBrace = new SingleCharacterParser('}');
    private final Parser<Character, Character> colon = new SingleCharacterParser(':');
    private final Parser<Character, Character> comma = new SingleCharacterParser(',');
    private final Parser<Character, JsonValue<?>> stringParser = new JsonStringParser();
    private final Parser<Character, String> whitespaceParser = new WhitespaceParser();

    @Override
    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        if(!openBrace.parse(input, offset).isMatch()){
            return new Match<JsonValue<?>>(null, 0);
        }
        int i = 1;
        JsonObject jsonObject = new JsonObject();
        //Handle empty object
        if(closeBrace.parse(input, offset + i).isMatch()){
            return new Match<JsonValue<?>>(jsonObject, i + 1);
        }
        while(true){
            Match<String> whitespaceMatch = whitespaceParser.parse(input, offset + i);
            i += whitespaceMatch.length;
            //Get key
            Match<JsonValue<?>> stringMatch = stringParser.parse(input, offset + i);
            if(!stringMatch.isMatch()){
                return new Match<JsonValue<?>>(null, 0);
            }
            i += stringMatch.length;
            whitespaceMatch = whitespaceParser.parse(input, offset + i);
            i += whitespaceMatch.length;
            //Colon
            Match<Character> colonMatch = colon.parse(input, offset + i);
            if(!colonMatch.isMatch()){
                return new Match<JsonValue<?>>(null, 0);
            }
            i += colonMatch.length;
            whitespaceMatch = whitespaceParser.parse(input, offset + i);
            i += whitespaceMatch.length;
            //Value
            Match<JsonValue<?>> valueMatch = new JsonParser().parse(input, offset + i);
            if(!valueMatch.isMatch()){
                return new Match<JsonValue<?>>(null, 0);
            }
            i += valueMatch.length;
            jsonObject.put((String) stringMatch.value.getValue(), valueMatch.value);
            whitespaceMatch = whitespaceParser.parse(input, offset + i);
            i += whitespaceMatch.length;
            //Comma or close brace
            Match<Character> commaMatch = comma.parse(input, offset + i);
            if(commaMatch.isMatch()){
                i += commaMatch.length;
            }else{
                break;
            }
        }
        Match<Character> closeBraceMatch = closeBrace.parse(input, offset + i);
        if(!closeBraceMatch.isMatch()){
            return new Match<JsonValue<?>>(null, 0);
        }
        i += closeBraceMatch.length;
        return new Match<JsonValue<?>>(jsonObject, i);
    }
    
}
