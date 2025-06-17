package io.dgj7.jod.core.diff.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.maps.diff.IMapDifferencer;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.core.recurse.action.IObjectGraphRecursor;
import io.dgj7.jod.core.recurse.predicate.IShouldRecursePredicate;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;
import io.dgj7.jod.xt.collections.detect.ICollectionDetector;
import io.dgj7.jod.xt.collections.transform.ICollectionTransformer;
import io.dgj7.jod.xt.enumerations.IEnumDetector;
import io.dgj7.jod.xt.equals.IEqualityChecker;
import io.dgj7.jod.xt.maps.detect.IMapDetector;
import io.dgj7.jod.xt.maps.transform.IMapTransformer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        final ICollectionDetector cd = config.getCollectionDetector();
        final ICollectionTransformer ct = config.getCollectionTransformer();
        final ICollectionDifferencer cdf = config.getCollectionDifferencer();
        final IMapDetector md = config.getMapDetector();
        final IMapTransformer mt = config.getMapTransformer();
        final IMapDifferencer mdf = config.getMapDifferencer();
        final IEnumDetector ed = config.getEnumDetector();
        final INullHandler nh = config.getNullHandler();
        final IEqualityChecker ec = config.getEqualityChecker();
        final IShouldRecursePredicate srp = config.getShouldRecursePredicate();
        final IObjectGraphRecursor ogr = config.getObjectGraphRecursor();

        if (expected == null || actual == null) {
            nh.handleNulls(config, path, deltas, expected, actual);
        } else if (ed.isEnum(expected, actual)) {
            if (!ec.check(expected, actual)) {
                deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
            }
        } else if (cd.isCollection(expected, actual)) {
            final Collection<Object> expectedCollection = ct.objectToCollection(expected);
            final Collection<Object> actualCollection = ct.objectToCollection(actual);
            cdf.diffCollections(config, deltas, path, expectedCollection, actualCollection);
        } else if (md.isMap(expected, actual)) {
            final Map<Object, Object> expectedMap = mt.objectToMap(expected);
            final Map<Object, Object> actualMap = mt.objectToMap(actual);
            mdf.diffMaps(config, deltas, path, expectedMap, actualMap);
        } else if (srp.test(config, expected, actual)) {
            ogr.diffRecurse(config, deltas, path, expected, actual);
        } else if (!ec.check(expected, actual)) {
            deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }
}
