package ca.awoo.json.types;

public class JsonNull extends JsonValue<Object> {

    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {
        super(null);
    }
    
}
