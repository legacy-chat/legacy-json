package ca.awoo.json;

/**
 * A class matcher that matches a single class.
 */
public class SingleClassMatcher implements ClassMatcher {
    private final Class<?> clazz;

    /**
     * Creates a new {@link SingleClassMatcher} that matches the given class.
     * @param clazz The class to match.
     */
    public SingleClassMatcher(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Returns true if the given class equals the class for this matcher.
     * @param clazz The class to match.
     * @return true if the given class equals this matcher's class, false otherwise.
     */
    public boolean matches(Class<?> clazz) {
        return this.clazz.equals(clazz);
    }
}
