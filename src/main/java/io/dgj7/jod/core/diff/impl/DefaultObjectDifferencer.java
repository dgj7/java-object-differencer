package io.dgj7.jod.core.diff.impl;

import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.collections.id.ICollectionIdentifier;
import io.dgj7.jod.core.collections.transform.ICollectionTransformer;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.recurse.action.IObjectGraphRecursor;
import io.dgj7.jod.core.enumerations.IEnumHandler;
import io.dgj7.jod.core.maps.diff.IMapDifferencer;
import io.dgj7.jod.core.maps.id.IMapIdentifier;
import io.dgj7.jod.core.maps.transform.IMapTransformer;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.core.recurse.predicate.IShouldRecursePredicate;
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
        final ICollectionIdentifier ci = config.getCollectionIdentifier();
        final ICollectionTransformer ct = config.getCollectionTransformer();
        final ICollectionDifferencer cd = config.getCollectionDifferencer();
        final IMapIdentifier mi = config.getMapIdentifier();
        final IMapTransformer mt = config.getMapTransformer();
        final IMapDifferencer md = config.getMapDifferencer();
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
            final Collection<Object> expectedCollection = ct.objectToCollection(expected);
            final Collection<Object> actualCollection = ct.objectToCollection(actual);
            cd.diffCollections(config, deltas, path, expectedCollection, actualCollection);
        } else if (mi.isMap(expected, actual)) {
            final Map<Object, Object> expectedMap = mt.objectToMap(expected);
            final Map<Object, Object> actualMap = mt.objectToMap(actual);
            md.diffMaps(config, deltas, path, expectedMap, actualMap);
        } else if (srp.test(config, expected, actual)) {
            ogr.diffRecurse(config, deltas, path, expected, actual);
        } else if (!et.test(expected, actual)) {
            deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }
}
