package io.dgj7.jod.core.diff.impl;

import io.dgj7.jod.core.diff.IDifferencer;
import io.dgj7.jod.model.Metadata;
import io.dgj7.jod.model.config.DiffConfig;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

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

        /* loop over fields */
        for (Field field : config.getReflection().fields(expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = config.getReflection().fieldTo(field, expected);
            final Object actualFieldValue = config.getReflection().fieldTo(field, actual);

            if (expectedFieldValue == null || actualFieldValue == null) {
                handleNulls(path, deltas, expectedFieldValue, actualFieldValue);
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
