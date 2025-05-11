package io.dgj7.jod.model.delta;

import lombok.Getter;

import java.util.Objects;

/**
 * <p>
 * Information about how to objects or fields differ.
 * </p>
 */
public class Delta {
    @Getter
    private final DeltaType type;
    @Getter
    private final String path;
    @Getter
    private final String expectedValue;
    @Getter
    private final String actualValue;

    /**
     * Create a new instance.
     */
    public Delta(final DeltaType pType, final String pPath, final String pExpectedValue, final String pActualValue) {
        this.type = Objects.requireNonNull(pType, "DeltaType is null");
        this.path = Objects.requireNonNull(pPath, "Path(String) is null");
        this.expectedValue = pExpectedValue;
        this.actualValue = pActualValue;
    }
}
