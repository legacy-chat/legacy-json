package ca.awoo.json.parsers;

import ca.awoo.json.types.JsonString;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.StreamException;

/**
 * A parser for {@link JsonString} objects.
 */
public class JsonStringParser extends Parser<Character, JsonValue<?>> {

    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        try {
            if(input.peek(offset) != '"'){
                return new Match<JsonValue<?>>(null, 0);
            }
            int i = 1;
            StringBuilder sb = new StringBuilder();
            while(true){
                char c = input.peek(i + offset);
                if(c == '\\'){
                    i++;
                    c = input.peek(i + offset);
                    switch(c){
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        char c1 = input.peek(++i + offset);
                        char c2 = input.peek(++i + offset);
                        char c3 = input.peek(++i + offset);
                        char c4 = input.peek(++i + offset);
                        sb.append((char)Integer.parseInt(new String(new char[]{c1,c2,c3,c4}), 16));
                        break;
                    default:
                        throw new ParseException("Invalid escape sequence: \\" + c);
                    }
                }else if(c == '"'){
                    return new Match<JsonValue<?>>(new JsonString(sb.toString()), i+1);
                }else{
                    sb.append(c);
                }
                i++;
            }
        } catch (StreamException e) {
            throw new ParseException("An exception occured while reading the stream",e);
        }
    }
    
}
