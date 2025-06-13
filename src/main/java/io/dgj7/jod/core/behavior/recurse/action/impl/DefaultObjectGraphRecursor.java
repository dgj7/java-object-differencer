package io.dgj7.jod.core.behavior.recurse.action.impl;

import io.dgj7.jod.core.behavior.diff.IObjectDifferencer;
import io.dgj7.jod.core.behavior.recurse.action.IObjectGraphRecursor;
import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.core.reflect.fields.IFieldsFinder;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

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
        final IReflection refl = config.getReflection();
        final IFieldsFinder fsf = config.getFieldsFinder();
        final IObjectDifferencer od = config.getObjectDifferencer();

        for (Field field : fsf.fields(config, expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = refl.fieldTo(field, expected);
            final Object actualFieldValue = refl.fieldTo(field, actual);

            od.diffObjects(config, deltas, path, expectedFieldValue, actualFieldValue);
        }
    }
}
