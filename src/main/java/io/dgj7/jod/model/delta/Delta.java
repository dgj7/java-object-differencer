package io.dgj7.jod.model.delta;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * <p>
 * Information about how to objects or fields differ.
 * </p>
 */
public class Delta {
    @Getter
    private final DeltaType deltaType;
    @Getter
    private final String path;
    @Getter
    private final String dataType;
    @Getter
    private String expectedValue;
    @Getter
    private String actualValue;

    /**
     * Create a new instance.
     */
    private Delta(final DeltaType pType, final String pPath, final String pDataType) {
        this.deltaType = Objects.requireNonNull(pType, "DeltaType is null");
        this.path = Objects.requireNonNull(pPath, "Path(String) is null");
        this.dataType = Objects.requireNonNull(pDataType, "DataType(String) is null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final String theDataType = StringUtils.isBlank(dataType) ? "" : " (" + dataType + ")";
        if (DeltaType.NO_MATCHING_ELEMENT.equals(deltaType)) {
            return deltaType + ": " + path + theDataType + ": no matching element for expected=[" + expectedValue + "]";
        } else if (DeltaType.COLLECTION_EXTRA_ACTUAL_ELEMENT.equals(deltaType)) {
            return deltaType + ": " + path + theDataType + ": extra unmatched element; actual=[" + actualValue + "]";
        } else {
            return deltaType + ": " + path + theDataType + ": expected=[" + expectedValue + "], actual=[" + actualValue + "]";
        }
    }

    /**
     * Factory.
     */
    public static <T, U> Delta from(final DeltaType deltaType, final String path, final T expected, final U actual) {
        final String dataType = determineTypeName(expected, actual);
        final Delta delta = new Delta(deltaType, path, dataType);

        delta.expectedValue = expected == null ? "null" : expected.toString();
        delta.actualValue = actual == null ? "null" : actual.toString();

        return delta;
    }

    /**
     * Factory.
     */
    public static <T> Delta noMatchingElement(final String path, final T expected) {
        final String dataType = determineTypeName(expected, null);

        final Delta delta = new Delta(DeltaType.NO_MATCHING_ELEMENT, path, dataType);

        delta.expectedValue = expected == null ? "null" : expected.toString();
        delta.actualValue = "null";

        return delta;
    }

    /**
     * Factory.
     */
    public static <T> Delta extraElement(final String path, final T actual) {
        final String dataType = determineTypeName(null, actual);

        final Delta delta = new Delta(DeltaType.COLLECTION_EXTRA_ACTUAL_ELEMENT, path, dataType);

        delta.expectedValue = "null";
        delta.actualValue = actual == null ? "null" : actual.toString();

        return delta;
    }

    /**
     * Determine the type name.
     */
    private static <T, U> String determineTypeName(final T expected, final U actual) {
        if (expected != null && actual != null) {
            if (expected.getClass().equals(actual.getClass())) {
                return expected.getClass().getName();
            } else {
                return expected.getClass().getName() + "/" + actual.getClass().getName();
            }
        } else if (expected != null) {
            return expected.getClass().getName();
        } else if (actual != null) {
            return actual.getClass().getName();
        } else {
            return "";
        }
    }
}
