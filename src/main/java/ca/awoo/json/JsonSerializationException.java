package ca.awoo.json;

public class JsonSerializationException extends JsonException {
    public Object obj;

    public JsonSerializationException(Object obj) {
        super("Could not serialize object: " + obj.toString() + " with type: " + obj.getClass().getName());
        this.obj = obj;
    }

    public JsonSerializationException(Object obj, String message) {
        super(message + ": " + obj.toString() + " with type: " + obj.getClass().getName());
        this.obj = obj;
    }

    public JsonSerializationException(Object obj, Throwable cause) {
        super("Could not serialize object: " + obj.toString() + " with type: " + obj.getClass().getName(), cause);
        this.obj = obj;
    }

    public JsonSerializationException(Object obj, String message, Throwable cause) {
        super(message + ": " + obj.toString() + " with type: " + obj.getClass().getName(), cause);
        this.obj = obj;
    }
}
