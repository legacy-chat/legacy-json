package ca.awoo.json;

/**
 * A class matcher that matches any class that implements {@link CustomSerializer}.
 */
public class CustomSerializationClassMatcher implements ClassMatcher {

    public boolean matches(Class<?> clazz) {
        return CustomSerializer.class.isAssignableFrom(clazz);
    }
    
}
