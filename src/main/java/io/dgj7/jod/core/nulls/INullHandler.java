package io.dgj7.jod.core.nulls;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * <p>
 * Handle when one of the two values is null.
 * </p>
 * <p>
 * Throws if both are non-null.
 * </p>
 */
public interface INullHandler {
    /**
     * Handle when one of two values is null.
     */
    <T> void handleNulls(final DifferencerConfiguration config, final String path, final List<Delta> deltas, final T expected, final T actual);
}
