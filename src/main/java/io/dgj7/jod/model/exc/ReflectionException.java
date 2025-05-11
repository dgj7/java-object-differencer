package io.dgj7.jod.model.exc;

public class ReflectionException extends RuntimeException {
    public ReflectionException(IllegalAccessException iaex) {
        super(iaex);
    }
}
