package io.dgj7.jod.core.diff;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * <p>
 * Diff two objects.
 * </p>
 * <p>
 * This needs to be it's own object because it's called from multiple clients.
 * </p>
 */
// todo: this needs a new name; should have 'Internal' in the name to clarify that clients shouldn't call it; also update the method name
public interface IObjectDifferencer {
    /**
     * <p>
     * Diff two objects.
     * </p>
     */
    <T> void diffObjects(final DifferencerConfiguration config, final List<Delta> deltas, final String path, final T expected, final T actual);
}
