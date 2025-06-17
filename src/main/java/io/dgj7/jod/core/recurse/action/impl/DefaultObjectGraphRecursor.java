package io.dgj7.jod.core.recurse.action.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.recurse.action.IObjectGraphRecursor;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.eq.EquatableThings;
import io.dgj7.jod.xt.reflect.IFieldFinder;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * Default {@link IObjectGraphRecursor}.
 * </p>
 */
public class DefaultObjectGraphRecursor implements IObjectGraphRecursor {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffRecurse(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual) {
        final IFieldFinder ff = config.getFieldFinder();
        final IObjectDifferencer od = config.getObjectDifferencer();
        final EquatableThings et = config.getEquatableThings();

        for (Field field : ff.fields(et, expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = ff.fieldToObject(field, expected);
            final Object actualFieldValue = ff.fieldToObject(field, actual);

            od.diffObjects(config, deltas, path, expectedFieldValue, actualFieldValue);
        }
    }
}
