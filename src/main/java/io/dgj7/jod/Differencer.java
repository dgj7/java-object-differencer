package io.dgj7.jod;

import io.dgj7.jod.core.collection.ICollectionHandler;
import io.dgj7.jod.core.collection.IMapHandler;
import io.dgj7.jod.model.Metadata;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.lang.reflect.Field;
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
        /* storage */
        final List<Delta> deltas = new LinkedList<>();
        final String path = determineRootPath(expected, actual);

        /* diff root objects, starting with null and type equality check, before recurse */
        if (expected == null || actual == null) {
            handleNulls(path, deltas, expected, actual);
        } else {
            final Metadata emd = Metadata.from(expected);
            final Metadata amd = Metadata.from(actual);
            if (emd.equals(amd)) {
                diffObjects(config, deltas, path, expected, actual);
            } else {
                deltas.add(Delta.from(DeltaType.DIFFERENT_TYPES, path, expected, actual));
            }
        }

        /* done */
        return deltas;
    }

    /**
     * Recursively iterate over object trees.
     */
    protected <T> void diffRecurse(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual) {
        /* loop over fields */
        for (Field field : config.getReflection().fields(expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = config.getReflection().fieldTo(field, expected);
            final Object actualFieldValue = config.getReflection().fieldTo(field, actual);

            diffObjects(config, deltas, path, expectedFieldValue, actualFieldValue);
        }
    }

    /**
     * Diff two objects.
     */
    protected <T> void diffObjects(final DifferencerConfiguration config, final List<Delta> deltas, final String path, final T expected, final T actual) {
        final ICollectionHandler ch = config.getCollectionHandler();
        final IMapHandler mh = config.getMapHandler();

        if (expected == null || actual == null) {
            handleNulls(path, deltas, expected, actual);
        } else if (config.getCollectionHandler().isCollection(expected, actual)) {
            diffLists(config, deltas, path, ch.findAllElements(expected), ch.findAllElements(actual));
        } else if (config.getMapHandler().isMap(expected, actual)) {
            diffMaps(config, deltas, path, mh.findAllElements(expected), mh.findAllElements(actual));
        } else if (config.getShouldRecurse().test(expected, actual)) {
            diffRecurse(config, deltas, path, expected, actual);
        } else if (!config.getEqualsTester().test(expected, actual)) {
            deltas.add(Delta.from(DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }

    /**
     * Handle lists.
     */
    protected <T> void diffLists(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final List<T> expectedList, final List<T> actualList) {
        if (expectedList.size() == actualList.size()) {
            for (int c = 0; c < expectedList.size(); c++) {
                final T expected = expectedList.get(c);
                final T actual = actualList.get(c);
                final String path = prefixPath + "[" + c + "]";
                diffRecurse(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(Delta.from(DeltaType.COLLECTION_SIZES_NOT_EQUAL, prefixPath, expectedList.size(), actualList.size()));
        }
    }

    /**
     * Handle maps.
     */
    protected <K, V> void diffMaps(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Map<K, V> expectedMap, final Map<K, V> actualMap) {
        if (expectedMap.size() == actualMap.size()) {
            for (K key : expectedMap.keySet()) {
                final V expected = expectedMap.get(key);
                final V actual = actualMap.get(key);
                final String path = prefixPath + "[" + key + "]";
                diffRecurse(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(Delta.from(DeltaType.MAP_SIZES_NOT_EQUAL, prefixPath, expectedMap.size(), actualMap.size()));
        }
    }

    /**
     * Handle nulls.
     */
    protected <T> void handleNulls(final String path, final List<Delta> deltas, final T expected, final T actual) {
        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            deltas.add(Delta.from(DeltaType.NULLITY, path, expected, actual));
        } else {
            throw new IllegalStateException("handleNulls() called with no nulls supplied");
        }
    }

    /**
     * Determine the initial path.
     */
    protected String determineRootPath(final Object expected, final Object actual) {
        if (expected != null) {
            final Metadata md = Metadata.from(expected);
            return md.getPackageName() + "." + md.getClassName();
        } else if (actual != null) {
            final Metadata md = Metadata.from(actual);
            return md.getPackageName() + "." + md.getClassName();
        } else {
            return "";
        }
    }
}
