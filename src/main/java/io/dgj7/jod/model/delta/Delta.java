package io.dgj7.jod.model.delta;

import io.dgj7.jod.model.Metadata;
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
        return deltaType + ": " + path + theDataType + ": expected=[" + expectedValue + "], actual=[" + actualValue + "]";
    }

    /**
     * Factory.
     */
    public static <T, U> Delta from(final DeltaType deltaType, final String path, final T expected, final U actual) {
        final String dataType = determineDataType(expected, actual);

        final Delta delta = new Delta(deltaType, path, dataType);

        delta.expectedValue = expected == null ? "null" : expected.toString();
        delta.actualValue = actual == null ? "null" : actual.toString();

        return delta;
    }

    private static <T, U> String determineDataType(final T expected, final U actual) {
        if (expected != null && actual != null) {
            final Metadata emd = Metadata.from(expected);
            final Metadata amd = Metadata.from(actual);

            if (emd.getPackageName().equals(amd.getPackageName()) && emd.getClassName().equals(amd.getClassName())) {
                return emd.getPackageName() + "." + emd.getClassName();
            } else {
                return emd.getPackageName() + "." + emd.getClassName() + "/" + amd.getPackageName() + "." + amd.getClassName();
            }
        } else if (expected != null) {
            final Metadata md = Metadata.from(expected);
            return md.getPackageName() + "." + md.getClassName();
        } else if (actual != null) {
            final Metadata md = Metadata.from(actual);
            return md.getPackageName() + "." + md.getClassName();
        } else {
            return "";
        }
    }
}
