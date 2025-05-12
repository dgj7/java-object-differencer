package io.dgj7.jod.core.diff.impl;

import io.dgj7.jod.core.components.collection.ICollectionHandler;
import io.dgj7.jod.core.components.collection.IMapHandler;
import io.dgj7.jod.core.diff.IDifferencer;
import io.dgj7.jod.model.Metadata;
import io.dgj7.jod.model.config.DiffConfig;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Default {@link IDifferencer} implementation.
 * </p>
 */
public class DefaultDifferencerImpl implements IDifferencer {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delta> difference(final DiffConfig config, final Object expected, final Object actual) {
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
                diffRecurse(config, deltas, path, expected, actual);
            } else {
                deltas.add(new Delta(DeltaType.DIFFERENT_TYPES, path, expected.getClass().getSimpleName(), actual.getClass().getSimpleName()));
            }
        }

        /* done */
        return deltas;
    }

    /**
     * Recursively iterate over object trees.
     */
    private <T> void diffRecurse(final DiffConfig config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual) {
        /* tools */
        final ICollectionHandler ch = config.getCollectionHandler();
        final IMapHandler mh = config.getMapHandler();

        /* loop over fields */
        for (Field field : config.getReflection().fields(expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = config.getReflection().fieldTo(field, expected);
            final Object actualFieldValue = config.getReflection().fieldTo(field, actual);

            if (expectedFieldValue == null || actualFieldValue == null) {
                handleNulls(path, deltas, expectedFieldValue, actualFieldValue);
            } else if (config.getCollectionHandler().isCollection(expectedFieldValue, actualFieldValue)) {
                diffLists(config, deltas, path, ch.findAllElements(expectedFieldValue), ch.findAllElements(actualFieldValue));
            } else if (config.getMapHandler().isMap(expectedFieldValue, actualFieldValue)) {
                diffMaps(config, deltas, path, mh.findAllElements(expectedFieldValue), mh.findAllElements(actualFieldValue));
            } else if (config.getShouldRecurse().test(expectedFieldValue, actualFieldValue)) {
                diffRecurse(config, deltas, path, expectedFieldValue, actualFieldValue);
            } else if (!config.getEqualsTester().test(expectedFieldValue, actualFieldValue)) {
                deltas.add(new Delta(DeltaType.NOT_EQUAL, path, expectedFieldValue.toString(), actualFieldValue.toString()));
            }
        }
    }

    /**
     * Handle lists.
     */
    private <T> void diffLists(final DiffConfig config, final List<Delta> deltas, final String prefixPath, final List<T> expectedList, final List<T> actualList) {
        if (expectedList.size() == actualList.size()) {
            for (int c = 0; c < expectedList.size(); c++) {
                final T expected = expectedList.get(c);
                final T actual = actualList.get(c);
                final String path = prefixPath + "[" + c + "]";
                diffRecurse(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(new Delta(DeltaType.COLLECTION_SIZES_NOT_EQUAL, prefixPath, "" + expectedList.size(), "" + actualList.size()));
        }
    }

    /**
     * Handle maps.
     */
    private <K, V> void diffMaps(final DiffConfig config, final List<Delta> deltas, final String prefixPath, final Map<K, V> expectedMap, final Map<K, V> actualMap) {
        if (expectedMap.size() == actualMap.size()) {
            for (K key : expectedMap.keySet()) {
                final V expected = expectedMap.get(key);
                final V actual = actualMap.get(key);
                final String path = prefixPath + "[" + key + "]";
                diffRecurse(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(new Delta(DeltaType.MAP_SIZES_NOT_EQUAL, prefixPath, "" + expectedMap.size(), "" + actualMap.size()));
        }
    }

    /**
     * Handle nulls.
     */
    private <T> void handleNulls(final String path, final List<Delta> deltas, final T expected, final T actual) {
        if (expected == null && actual == null) {
            return;
        } else if (expected == null) {
            deltas.add(new Delta(DeltaType.NULLITY, path, "null", actual.getClass().getSimpleName()));
        } else if (actual == null) {
            deltas.add(new Delta(DeltaType.NULLITY, path, expected.getClass().getSimpleName(), "null"));
        } else {
            throw new IllegalStateException("handleNulls() called with no nulls supplied");
        }
    }

    /**
     * Determine the initial path.
     */
    private String determineRootPath(final Object expected, final Object actual) {
        if (expected != null) {
            final Metadata md = Metadata.from(expected);
            return md.getPackageName() + md.getClassName();
        } else if (actual != null) {
            final Metadata md = Metadata.from(actual);
            return md.getPackageName() + md.getClassName();
        } else {
            return "";
        }
    }
}
