package ca.awoo.json.types;

public class JsonString extends JsonValue<String>{

    public JsonString(String value) {
        super(value);
    }

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

    @Override
    public String toString() {
        return "\"" + escape(value) + "\"";
    }
    
}
