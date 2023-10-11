package ca.awoo.json;

/**
 * A class matcher that matches an array class.
 */
public class ArrayClassMatcher implements ClassMatcher{

    /**
     * Returns true if the given class is an array.
     * @param clazz The class to match.
     * @return true if the given class is an array, false otherwise.
     */
    public boolean matches(Class<?> clazz) {
        return clazz.isArray();
    }
    
}
