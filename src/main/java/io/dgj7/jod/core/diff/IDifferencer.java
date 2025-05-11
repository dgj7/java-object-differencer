package io.dgj7.jod.core.diff;

import io.dgj7.jod.model.config.DiffConfig;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * <p>
 * Interface for differencer implementations.
 * </p>
 */
public interface IDifferencer {
    /**
     * Find differences in the form of deltas.
     */
    List<Delta> difference(final DiffConfig config, final Object left, final Object right);
}
