package io.dgj7.jod;

import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.core.path.IRootPathProvider;
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
     * {@inheritDoc}
     */
    public List<Delta> difference(final DifferencerConfiguration config, final Object expected, final Object actual) {
        /* retrieve behaviors */
        final IObjectDifferencer od = config.getObjectDifferencer();
        final INullHandler nh = config.getNullHandler();
        final IMetaDataFactory<? extends AbstractMetaData> mdf = config.getMetaDataFactory();
        final IRootPathProvider rpp = config.getRootPathProvider();

        /* storage */
        final List<Delta> deltas = new LinkedList<>();
        final String path = rpp.provideRootPath(config, expected, actual);

        /* diff root objects, starting with null and type equality check, before recurse */
        if (expected == null || actual == null) {
            nh.handleNulls(config, path, deltas, expected, actual);
        } else {
            final AbstractMetaData emd = mdf.from(expected);
            final AbstractMetaData amd = mdf.from(actual);
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
