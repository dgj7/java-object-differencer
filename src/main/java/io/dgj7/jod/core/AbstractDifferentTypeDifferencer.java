package io.dgj7.jod.core;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Difference two objects that may be different types.
 * </p>
 */
public class AbstractDifferentTypeDifferencer extends AbstractSameTypeDifferencer {
    /**
     * Create a new instance.
     */
    protected AbstractDifferentTypeDifferencer(final DifferencerConfiguration config) {
        super(config);
    }

    /**
     * Find differences between two objects that may be different types.
     */
    protected <T, U> List<Delta> differenceDifferentTypes(final T expected, final U actual) {
        /* storage */
        final List<Delta> deltas = new LinkedList<>();

        /* determine the root path */
        final String path = rpp.provideRootPath(expected, actual);

        /* diff root objects, starting with null and type equality check, before recurse */
        if (expected == null || actual == null) {
            nh.handleNulls(path, deltas, expected, actual);
        } else {
            /* if objects are same type, we can begin to actually diff them; otherwise, add delta and exit */
            if (expected.getClass().equals(actual.getClass())) {
                differenceSameTypes(deltas, path, expected, actual);
            } else {
                deltas.add(Delta.from(DeltaType.DIFFERENT_TYPES, path, expected, actual));
            }
        }

        /* done */
        return deltas;
    }
}
