package ca.awoo.json.types;

public abstract class JsonValue<T> {
    T value;
    public JsonValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T val) {
        value = val;
    }

    public String toString() {
        return value.toString();
    }
}
