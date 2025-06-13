package io.dgj7.jod;

import io.dgj7.jod.core.behavior.diff.IObjectDifferencer;
import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.core.behavior.nulls.INullHandler;
import io.dgj7.jod.core.behavior.path.IRootPathProvider;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.LinkedList;
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
public class Differencer {
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

    /**
     * <p>
     * Find and return a list of differences between the two given objects, using the given configuration.
     * </p>
     */
    public List<Delta> difference(final DifferencerConfiguration config, final Object expected, final Object actual) {
        /* retrieve behaviors */
        final IObjectDifferencer od = config.getObjectDifferencer();
        final INullHandler nh = config.getNullHandler();
        final IMetaDataFactory<? extends AbstractMetaData> mdf = config.getMetaDataFactory();
        final IRootPathProvider rpp = config.getRootPathProvider();

        /* storage */
        final List<Delta> deltas = new LinkedList<>();

        /* determine the root path */
        final String path = rpp.provideRootPath(config, expected, actual);

        /* diff root objects, starting with null and type equality check, before recurse */
        if (expected == null || actual == null) {
            nh.handleNulls(config, path, deltas, expected, actual);
        } else {
            final AbstractMetaData emd = mdf.from(expected);
            final AbstractMetaData amd = mdf.from(actual);

            /* if objects are same type, we can begin to actually diff them; otherwise, add delta and exit */
            if (emd.equals(amd)) {
                od.diffObjects(config, deltas, path, expected, actual);
            } else {
                deltas.add(Delta.from(config, DeltaType.DIFFERENT_TYPES, path, expected, actual));
            }
        }

        /* done */
        return deltas;
    }
}
