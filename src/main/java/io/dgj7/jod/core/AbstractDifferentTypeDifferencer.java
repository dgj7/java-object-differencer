package io.dgj7.jod.core;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.metadata.AbstractMetaData;
import io.dgj7.jod.metadata.IMetaDataFactory;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;
import io.dgj7.jod.xt.nulls.INullHandler;
import io.dgj7.jod.xt.path.IRootPathProvider;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Difference two objects that may be different types.
 * </p>
 */
public class AbstractDifferentTypeDifferencer extends AbstractSameTypeDifferencer {
    /**
     * Find differences between two objects that may be different types.
     */
    protected <T, U> List<Delta> differenceDifferentTypes(final DifferencerConfiguration config, final T expected, final U actual) {
        /* retrieve behaviors */
        final INullHandler nh = config.getNullHandler();
        final IMetaDataFactory<? extends AbstractMetaData> mdf = config.getMetaDataFactory();
        final IRootPathProvider rpp = config.getRootPathProvider();

        /* storage */
        final List<Delta> deltas = new LinkedList<>();

        /* determine the root path */
        final String path = rpp.provideRootPath(expected, actual);

        /* diff root objects, starting with null and type equality check, before recurse */
        if (expected == null || actual == null) {
            nh.handleNulls(path, deltas, expected, actual);
        } else {
            final AbstractMetaData emd = mdf.from(config, expected);
            final AbstractMetaData amd = mdf.from(config, actual);

            /* if objects are same type, we can begin to actually diff them; otherwise, add delta and exit */
            if (emd.equals(amd)) {
                differenceSameTypes(config, deltas, path, expected, actual);
            } else {
                deltas.add(Delta.from(DeltaType.DIFFERENT_TYPES, path, expected, actual));
            }
        }

        /* done */
        return deltas;
    }
}
