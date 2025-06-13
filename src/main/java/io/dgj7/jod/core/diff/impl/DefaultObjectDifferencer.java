package io.dgj7.jod.core.diff.impl;

import io.dgj7.jod.core.collections.ICollectionHandler;
import io.dgj7.jod.core.collections.id.ICollectionIdentifier;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.diff.IObjectGraphRecursor;
import io.dgj7.jod.core.enumerations.IEnumHandler;
import io.dgj7.jod.core.maps.IMapHandler;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.core.recurse.IShouldRecursePredicate;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * <p>
 * Default {@link IObjectDifferencer}.
 * </p>
 */
public class DefaultObjectDifferencer implements IObjectDifferencer {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffObjects(final DifferencerConfiguration config, final List<Delta> deltas, final String path, final T expected, final T actual) {
        final ICollectionIdentifier ci = config.getCollectionIdentifier();
        final ICollectionHandler ch = config.getCollectionHandler();
        final IMapHandler mh = config.getMapHandler();
        final IEnumHandler eh = config.getEnumHandler();
        final INullHandler nh = config.getNullHandler();
        final BiPredicate<Object, Object> et = config.getEqualsTester();
        final IShouldRecursePredicate srp = config.getShouldRecurse();
        final IObjectGraphRecursor ogr = config.getRecursor();

        if (expected == null || actual == null) {
            nh.handleNulls(config, path, deltas, expected, actual);
        } else if (eh.isEnum(expected, actual)) {
            if (!et.test(expected, actual)) {
                deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
            }
        } else if (ci.isCollection(expected, actual)) {
            ch.diffCollections(config, deltas, path, ch.findCollection(expected), ch.findCollection(actual));
        } else if (mh.isMap(expected, actual)) {
            mh.diffMaps(config, deltas, path, mh.findAllElements(expected), mh.findAllElements(actual));
        } else if (srp.test(config, expected, actual)) {
            ogr.diffRecurse(config, deltas, path, expected, actual);
        } else if (!et.test(expected, actual)) {
            deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }
}
