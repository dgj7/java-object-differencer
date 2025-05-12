package io.dgj7.jod.model.exc;

/**
 * Exception in reflection.
 */
public class ReflectionException extends RuntimeException {
    /**
     * Create a new instance.
     */
    public ReflectionException(IllegalAccessException iaex) {
        super(iaex);
    }
}
