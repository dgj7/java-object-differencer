package io.dgj7.jod.core.diff;

import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * <p>
 * Recurse into two objects, field by field.
 * </p>
 */
public interface IObjectGraphRecursor {
    /**
     * Recurse into two objects.
     */
    <T> void diffRecurse(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual);
}
