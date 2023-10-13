package ca.awoo.json.parsers;

import java.math.BigDecimal;
import java.util.Collection;

import ca.awoo.fwoabl.Optional;
import ca.awoo.json.types.JsonNumber;
import ca.awoo.json.types.JsonValue;
import ca.awoo.praser.InputStreamOf;
import ca.awoo.praser.ParseException;
import ca.awoo.praser.Parser;
import ca.awoo.praser.character.CharInStringParser;
import ca.awoo.praser.character.SingleCharacterParser;
import ca.awoo.praser.parsers.ZeroOrMoreParser;
import ca.awoo.praser.parsers.ZeroOrOneParser;

/**
 * A parser for {@link JsonNumber} objects.
 */
public class JsonNumberParser extends Parser<Character, JsonValue<?>> {

    private final Parser<Character, Optional<Character>> optionalSign = new ZeroOrOneParser<Character, Character>(new SingleCharacterParser('-'));
    private final Parser<Character, Character> digit = new CharInStringParser("0123456789");
    private final Parser<Character, Collection<Character>> digits = new ZeroOrMoreParser<Character, Character>(digit);
    private final Parser<Character, Character> dot = new SingleCharacterParser('.');
    private final Parser<Character, Character> e = new CharInStringParser("eE");
    private final Parser<Character, Optional<Character>> plusOrMinusOrNone = new ZeroOrOneParser<Character, Character>(
            new CharInStringParser("+-")
    );


    @Override
    public Match<JsonValue<?>> parse(InputStreamOf<Character> input, int offset) throws ParseException {
        StringBuilder sb = new StringBuilder();
        //Parse optional sign
        Match<Optional<Character>> optionalCharMatch = optionalSign.parse(input, offset);
        if(optionalCharMatch.isMatch()){
            sb.append(optionalCharMatch.value.isSome() ? optionalCharMatch.value.get() : "");
        }
        int i = optionalCharMatch.length;
        //Parse digits
        Match<Character> charMatch = digit.parse(input, offset + i);
        if(!charMatch.isMatch()){
            return new Match<JsonValue<?>>(null, 0);
        }
        sb.append(charMatch.value);
        i += charMatch.length;
        //Leading zeros is verboten
        if(charMatch.value != '0'){
            Match<Collection<Character>> digitsMatch = digits.parse(input, offset + i);
            if(digitsMatch.isMatch()){
                for(Character c : digitsMatch.value){
                    sb.append(c);
                }
                i += digitsMatch.length;
            }
        }
        //Parse optional dot and fraction part
        charMatch = dot.parse(input, offset + i);
        if(charMatch.isMatch()){
            sb.append(charMatch.value);
            i += charMatch.length;
            Match<Collection<Character>> digitsMatch = digits.parse(input, offset + i);
            if(digitsMatch.isMatch() && digitsMatch.value.size() > 0){
                for(Character c : digitsMatch.value){
                    sb.append(c);
                }
                i += digitsMatch.length;
            }else{
                //If there is a dot, there must be a fraction part
                return new Match<JsonValue<?>>(null, 0);
            }
        }
        //Parse optional e and exponent part
        charMatch = e.parse(input, offset + i);
        if(charMatch.isMatch()){
            sb.append(charMatch.value);
            i += charMatch.length;
            //There may be a plus or minus or nothing
            optionalCharMatch = plusOrMinusOrNone.parse(input, offset + i);
            if(optionalCharMatch.isMatch()){
                sb.append(optionalCharMatch.value.isSome() ? optionalCharMatch.value.get() : "");
                i += optionalCharMatch.length;
            }
            Match<Collection<Character>> digitsMatch = digits.parse(input, offset + i);
            if(digitsMatch.isMatch() && digitsMatch.value.size() > 0){
                for(Character c : digitsMatch.value){
                    sb.append(c);
                }
                i += digitsMatch.length;
            }else{
                //If there is an e, there must be an exponent part
                return new Match<JsonValue<?>>(null, 0);
            }
        }
        BigDecimal bd = new BigDecimal(sb.toString());
        return new Match<JsonValue<?>>(new JsonNumber(bd), i);
    }
    
}
