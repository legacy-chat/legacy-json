package ca.awoo.json;

public class CustomSerializationClassMatcher implements ClassMatcher {

    public boolean matches(Class<?> clazz) {
        return CustomSerializer.class.isAssignableFrom(clazz);
    }
    
}
