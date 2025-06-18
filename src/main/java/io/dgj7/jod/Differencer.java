package io.dgj7.jod;

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
import io.dgj7.jod.xt.path.IRootPathProvider;
import io.dgj7.jod.xt.recurse.IShouldRecursePredicate;
import io.dgj7.jod.xt.reflect.IFieldFinder;

import java.lang.reflect.Field;
import java.util.*;

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
    private final IFieldFinder ff;
    private final IShouldRecursePredicate srp;
    private final IEqualityChecker ec;
    private final ICollectionDetector cd;
    private final ICollectionTransformer ct;
    private final IMapDetector md;
    private final IMapTransformer mt;
    private final IEnumDetector ed;
    private final IRootPathProvider rpp;
    private final INullHandler nh;
    private final EquatableThings et;

    /**
     * Create a new instance.
     */
    public Differencer() {
        this(DifferencerConfiguration.builder().build());
    }

    /**
     * Create a new instance.
     */
    public Differencer(final DifferencerConfiguration config) {
        Objects.requireNonNull(config, "DifferencerConfig is null");
        this.ff = config.getFieldFinder();
        this.srp = config.getShouldRecursePredicate();
        this.ec = config.getEqualityChecker();
        this.cd = config.getCollectionDetector();
        this.ct = config.getCollectionTransformer();
        this.md = config.getMapDetector();
        this.mt = config.getMapTransformer();
        this.ed = config.getEnumDetector();
        this.rpp = config.getRootPathProvider();
        this.nh = config.getNullHandler();
        this.et = config.getEquatableThings();
    }

    /**
     * <p>
     * Find and return a list of differences between the two given objects.
     * </p>
     */
    public List<Delta> difference(final Object expected, final Object actual) {
        return differenceDifferentTypes(expected, actual);
    }

    /**
     * Find differences between two objects that may be different types.
     */
    private <T, U> List<Delta> differenceDifferentTypes(final T expected, final U actual) {
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

    /**
     * Difference two objects of the same type.
     */
    private <T> void differenceSameTypes(final List<Delta> deltas, final String path, final T expected, final T actual) {
        if (expected == null || actual == null) {
            nh.handleNulls(path, deltas, expected, actual);
        } else if (ed.isEnum(expected, actual)) {
            if (!ec.check(expected, actual)) {
                deltas.add(Delta.from(DeltaType.NOT_EQUAL, path, expected, actual));
            }
        } else if (cd.isCollection(expected, actual)) {
            final Collection<Object> expectedCollection = ct.objectToCollection(expected);
            final Collection<Object> actualCollection = ct.objectToCollection(actual);
            differenceCollections(deltas, path, expectedCollection, actualCollection);
        } else if (md.isMap(expected, actual)) {
            final Map<Object, Object> expectedMap = mt.objectToMap(expected);
            final Map<Object, Object> actualMap = mt.objectToMap(actual);
            differenceMaps(deltas, path, expectedMap, actualMap);
        } else if (srp.test(et, expected, actual)) {
            differenceRecurse(deltas, path, expected, actual);
        } else if (!ec.check(expected, actual)) {
            deltas.add(Delta.from(DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }

    /**
     * Recurse into the fields of both objects.
     */
    private <T> void differenceRecurse(final List<Delta> deltas, final String prefixPath, final T expected, final T actual) {
        for (Field field : ff.findFields(et, expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = ff.fieldToObject(field, expected);
            final Object actualFieldValue = ff.fieldToObject(field, actual);

            differenceSameTypes(deltas, path, expectedFieldValue, actualFieldValue);
        }
    }

    /**
     * Find differences between two collections.
     */
    private <T> void differenceCollections(final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList) {
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
                    differenceSameTypes(deltas, path, expected, actual);
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
    private <K, V> void differenceMaps(final List<Delta> deltas, final String prefixPath, final Map<K, V> expectedMap, final Map<K, V> actualMap) {
        if (expectedMap.size() == actualMap.size()) {
            for (K key : expectedMap.keySet()) {
                final V expected = expectedMap.get(key);
                final V actual = actualMap.get(key);
                final String path = prefixPath + "[" + key + "]";
                differenceSameTypes(deltas, path, expected, actual);
            }
        } else {
            deltas.add(Delta.from(DeltaType.MAP_SIZES_NOT_EQUAL, prefixPath, expectedMap.size(), actualMap.size()));
        }
    }
}
