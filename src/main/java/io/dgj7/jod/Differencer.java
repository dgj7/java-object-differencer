package io.dgj7.jod;

import io.dgj7.jod.core.AbstractDifferentTypeDifferencer;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * <p>
 * Object differencer implementation.
 * </p>
 * <p>
 * helper methods in this class are protected, so that it's easy
 * to extend this class and modify how those methods work, if needed.
 * </p>
 */
public class Differencer extends AbstractDifferentTypeDifferencer {
    /**
     * <p>
     * Find and return a list of differences between the two given objects.
     * </p>
     * <p>
     * This method uses the default configuration.
     * </p>
     */
    public List<Delta> difference(final Object expected, final Object actual) {
        final DifferencerConfiguration config = DifferencerConfiguration.builder().build();
        return difference(config, expected, actual);
    }

    public List<Delta> difference(final DifferencerConfiguration config, final Object expected, final Object actual) {
        return differenceDifferentTypes(config, expected, actual);
    }
}
