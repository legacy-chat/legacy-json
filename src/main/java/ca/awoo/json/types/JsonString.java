package ca.awoo.json.types;

/**
 * Represents a JSON string.
 */
public class JsonString extends JsonValue<String>{

    /**
     * Creates a new JsonString with the given value.
     * @param value The value of the string.
     */
    public JsonString(String value) {
        super(value);
    }

    /**
     * Escapes the given string so that it can be used in a JSON string.
     * Do not use this when creating a JsonString, the JsonString class stores the string unescaped and escapes it when toString() is called.
     * This is provided publicly because there are points in the JSON format that use strings that are not JsonStrings, such as object keys.
     * @param str The string to escape.
     * @return The escaped string.
     */
    public static String escape(String str){
        //Escape characters
        str = str.replace("\\", "\\\\"); //Backslash
        str = str.replace("\"", "\\\""); //Double quote
        str = str.replace("\b", "\\b"); //Backspace
        str = str.replace("\f", "\\f"); //Form feed
        str = str.replace("\n", "\\n"); //Newline
        str = str.replace("\r", "\\r"); //Carriage return
        str = str.replace("\t", "\\t"); //Tab
        return str;
    }

    /**
     * Gets the string representation of the JSON string.
     * This will escape the string and add quotes around it.
     */
    @Override
    public String toString() {
        return "\"" + escape(value) + "\"";
    }
    
}
