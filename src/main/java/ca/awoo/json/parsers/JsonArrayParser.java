package ca.awoo.json.parsers;

import ca.awoo.json.types.JsonArray;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.character.SingleCharacterParser;

public class JsonArrayParser extends Parser<Character, JsonValue<?>> {

    private final Parser<Character, Character> openBracket = new SingleCharacterParser('[');
    private final Parser<Character, Character> closeBracket = new SingleCharacterParser(']');
    private final Parser<Character, Character> comma = new SingleCharacterParser(',');

    @Override
    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        Match<Character> openBracketMatch = openBracket.parse(input, offset);
        if(!openBracketMatch.isMatch()){
            return new Match<JsonValue<?>>(null, 0);
        }
        int i = openBracketMatch.length;
        JsonArray jsonArray = new JsonArray();
        while(true){
            Match<JsonValue<?>> valueMatch = new JsonParser().parse(input, offset + i);
            if(!valueMatch.isMatch()){
                return new Match<JsonValue<?>>(null, 0);
            }
            i += valueMatch.length;
            jsonArray.add(valueMatch.value);
            Match<Character> commaMatch = comma.parse(input, offset + i);
            if(commaMatch.isMatch()){
                i += commaMatch.length;
            }else{
                break;
            }
        }
        Match<Character> closeBracketMatch = closeBracket.parse(input, offset + i);
        if(!closeBracketMatch.isMatch()){
            return new Match<JsonValue<?>>(null, 0);
        }
        i += closeBracketMatch.length;
        return new Match<JsonValue<?>>(jsonArray, i);
    }
    
}
