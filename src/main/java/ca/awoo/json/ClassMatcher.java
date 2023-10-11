package ca.awoo.json;

/**
 * A class matcher is used to determine if a class matches a certain pattern.
 * <p>
 * This is used by {@link Json} to determine which {@link Serializer} to use for a given class.
 * </p>
 */
public interface ClassMatcher {
    /**
     * Returns true if the given class matches the pattern.
     * @param clazz The class to match.
     * @return true if the given class matches the pattern, false otherwise.
     */
    public boolean matches(Class<?> clazz);
}
