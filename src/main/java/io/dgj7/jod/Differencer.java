package io.dgj7.jod;

import io.dgj7.jod.core.diff.IDifferencerInternals;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.model.Metadata;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Object differencer implementation.
 * </p>
 * <p>
 * helper methods in this class are protected, so that it's easy
 * to extend this class and modify how those methods work, if needed.
 * </p>
 */
public class Differencer {
    /**
     * {@inheritDoc}
     */
    public List<Delta> difference(final DifferencerConfiguration config, final Object expected, final Object actual) {
        /* retrieve behaviors */
        final IDifferencerInternals internals = config.getDifferencerInternals();
        final INullHandler nh = config.getNullHandler();

        /* storage */
        final List<Delta> deltas = new LinkedList<>();
        final String path = config.getRootPathProvider().provideRootPath(expected, actual);

        /* diff root objects, starting with null and type equality check, before recurse */
        if (expected == null || actual == null) {
            nh.handleNulls(path, deltas, expected, actual);
        } else {
            final Metadata emd = Metadata.from(expected);
            final Metadata amd = Metadata.from(actual);
            if (emd.equals(amd)) {
                internals.diffObjects(config, deltas, path, expected, actual);
            } else {
                deltas.add(Delta.from(DeltaType.DIFFERENT_TYPES, path, expected, actual));
            }
        }

        /* done */
        return deltas;
    }
}
