package io.dgj7.jod.core.behavior.diff.impl;

import io.dgj7.jod.core.behavior.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.behavior.collections.detect.ICollectionDetector;
import io.dgj7.jod.core.behavior.collections.transform.ICollectionTransformer;
import io.dgj7.jod.core.behavior.diff.IObjectDifferencer;
import io.dgj7.jod.core.behavior.recurse.action.IObjectGraphRecursor;
import io.dgj7.jod.core.behavior.enumerations.IEnumDetector;
import io.dgj7.jod.core.behavior.maps.diff.IMapDifferencer;
import io.dgj7.jod.core.behavior.maps.detect.IMapDetector;
import io.dgj7.jod.core.behavior.maps.transform.IMapTransformer;
import io.dgj7.jod.core.behavior.nulls.INullHandler;
import io.dgj7.jod.core.behavior.recurse.predicate.IShouldRecursePredicate;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
        final ICollectionDetector cd = config.getCollectionDetector();
        final ICollectionTransformer ct = config.getCollectionTransformer();
        final ICollectionDifferencer cdf = config.getCollectionDifferencer();
        final IMapDetector md = config.getMapDetector();
        final IMapTransformer mt = config.getMapTransformer();
        final IMapDifferencer mdf = config.getMapDifferencer();
        final IEnumDetector ed = config.getEnumDetector();
        final INullHandler nh = config.getNullHandler();
        final BiPredicate<Object, Object> et = config.getEqualsTester();
        final IShouldRecursePredicate srp = config.getShouldRecurse();
        final IObjectGraphRecursor ogr = config.getRecursor();

        if (expected == null || actual == null) {
            nh.handleNulls(config, path, deltas, expected, actual);
        } else if (ed.isEnum(expected, actual)) {
            if (!et.test(expected, actual)) {
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
        } else if (!et.test(expected, actual)) {
            deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }
}
