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
                deltas.addAll(diffRecurse(config, path, expected, actual));
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
    private <T> List<Delta> diffRecurse(final DiffConfig config, final String prefixPath, final T expected, final T actual) {
        /* storage */
        final List<Delta> deltas = new LinkedList<>();

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
                deltas.addAll(diffLists(config, path, ch.findAllElements(expectedFieldValue), ch.findAllElements(actualFieldValue)));
            } else if (config.getMapHandler().isMap(expectedFieldValue, actualFieldValue)) {
                deltas.addAll(diffMaps(config, path, mh.findAllElements(expectedFieldValue), mh.findAllElements(actualFieldValue)));
            } else if (config.getShouldRecurse().test(expectedFieldValue, actualFieldValue)) {
                deltas.addAll(diffRecurse(config, path, expectedFieldValue, actualFieldValue));
            } else if (!config.getEqualsTester().test(expectedFieldValue, actualFieldValue)) {
                deltas.add(new Delta(DeltaType.NOT_EQUAL, path, expectedFieldValue.toString(), actualFieldValue.toString()));
            }
        }

        /* done */
        return deltas;
    }

    /**
     * Handle lists.
     */
    private <T> List<Delta> diffLists(final DiffConfig config, final String prefixPath, final List<T> expected, final List<T> actual) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Handle maps.
     */
    private <K, V> List<Delta> diffMaps(final DiffConfig config, final String prefixPath, final Map<K, V> expected, final Map<K, V> actual) {
        throw new UnsupportedOperationException("not yet implemented");
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
