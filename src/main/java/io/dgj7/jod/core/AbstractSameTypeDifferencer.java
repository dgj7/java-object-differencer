package io.dgj7.jod.core;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;
import io.dgj7.jod.model.eq.EquatableThings;
import io.dgj7.jod.xt.collections.detect.ICollectionDetector;
import io.dgj7.jod.xt.collections.transform.ICollectionTransformer;
import io.dgj7.jod.xt.enumerations.IEnumDetector;
import io.dgj7.jod.xt.equals.IEqualityChecker;
import io.dgj7.jod.xt.maps.detect.IMapDetector;
import io.dgj7.jod.xt.maps.transform.IMapTransformer;
import io.dgj7.jod.xt.nulls.INullHandler;
import io.dgj7.jod.xt.recurse.IShouldRecursePredicate;
import io.dgj7.jod.xt.reflect.IFieldFinder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Difference two objects of the same type.
 * </p>
 */
public class AbstractSameTypeDifferencer {
    /**
     * Difference two objects of the same type.
     */
    protected <T> void differenceSameTypes(final DifferencerConfiguration config, final List<Delta> deltas, final String path, final T expected, final T actual) {
        final ICollectionDetector cd = config.getCollectionDetector();
        final ICollectionTransformer ct = config.getCollectionTransformer();
        final IMapDetector md = config.getMapDetector();
        final IMapTransformer mt = config.getMapTransformer();
        final IEnumDetector ed = config.getEnumDetector();
        final INullHandler nh = config.getNullHandler();
        final IEqualityChecker ec = config.getEqualityChecker();
        final IShouldRecursePredicate srp = config.getShouldRecursePredicate();
        final EquatableThings et = config.getEquatableThings();

        if (expected == null || actual == null) {
            nh.handleNulls(path, deltas, expected, actual);
        } else if (ed.isEnum(expected, actual)) {
            if (!ec.check(expected, actual)) {
                deltas.add(Delta.from(DeltaType.NOT_EQUAL, path, expected, actual));
            }
        } else if (cd.isCollection(expected, actual)) {
            final Collection<Object> expectedCollection = ct.objectToCollection(expected);
            final Collection<Object> actualCollection = ct.objectToCollection(actual);
            differenceCollections(config, deltas, path, expectedCollection, actualCollection);
        } else if (md.isMap(expected, actual)) {
            final Map<Object, Object> expectedMap = mt.objectToMap(expected);
            final Map<Object, Object> actualMap = mt.objectToMap(actual);
            differenceMaps(config, deltas, path, expectedMap, actualMap);
        } else if (srp.test(et, expected, actual)) {
            differenceRecurse(config, deltas, path, expected, actual);
        } else if (!ec.check(expected, actual)) {
            deltas.add(Delta.from(DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }

    /**
     * Recurse into the fields of both objects.
     */
    public <T> void differenceRecurse(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual) {
        final IFieldFinder ff = config.getFieldFinder();
        final EquatableThings et = config.getEquatableThings();

        for (Field field : ff.fields(et, expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = ff.fieldToObject(field, expected);
            final Object actualFieldValue = ff.fieldToObject(field, actual);

            differenceSameTypes(config, deltas, path, expectedFieldValue, actualFieldValue);
        }
    }

    /**
     * Find differences between two collections.
     */
    private <T> void differenceCollections(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList) {
        /* add delta if collections are different sizes */
        if (expectedList.size() != actualList.size()) {
            deltas.add(Delta.from(DeltaType.COLLECTION_SIZES_NOT_EQUAL, prefixPath, expectedList.size(), actualList.size()));
        }

        /* get "iterators" */
        final List<T> actualListCopy = new ArrayList<>(actualList);
        int c = 0;

        /* attempt to find a match for each element in the expected collection */
        for (T expected : expectedList) {
            final String path = prefixPath + "[" + c++ + "]";
            if (expected == null) {
                int index = -1;
                for (int d = 0; d < actualListCopy.size(); d++) {
                    if (actualListCopy.get(d) == null) {
                        index = d;
                    }
                }
                if (index >= 0) {
                    actualListCopy.remove(index);
                } else {
                    deltas.add(Delta.noMatchingElement(path, "null"));
                }
            } else {
                if (actualListCopy.isEmpty()) {
                    deltas.add(Delta.noMatchingElement(path, expected));
                } else {
                    final T actual = actualListCopy.get(0);
                    differenceSameTypes(config, deltas, path, expected, actual);
                    actualListCopy.remove(actual);
                }
            }
        }

        /* add a diff for any 'extra' elements in the actual list */
        for (int e = 0; e < actualListCopy.size(); e++) {
            final T extra = actualListCopy.get(e);
            deltas.add(Delta.extraElement(prefixPath + "[" + c + "+" + (e + 1) + "]", extra));
        }
    }

    /**
     * Find differences between two maps.
     */
    private <K, V> void differenceMaps(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Map<K, V> expectedMap, final Map<K, V> actualMap) {
        if (expectedMap.size() == actualMap.size()) {
            for (K key : expectedMap.keySet()) {
                final V expected = expectedMap.get(key);
                final V actual = actualMap.get(key);
                final String path = prefixPath + "[" + key + "]";
                differenceSameTypes(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(Delta.from(DeltaType.MAP_SIZES_NOT_EQUAL, prefixPath, expectedMap.size(), actualMap.size()));
        }
    }
}
