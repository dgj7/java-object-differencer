package io.dgj7.jod.core.diff;

import io.dgj7.jod.core.collections.ICollectionHandler;
import io.dgj7.jod.core.enumerations.IEnumHandler;
import io.dgj7.jod.core.maps.IMapHandler;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * Default {@link IDifferencerInternals}.
 * </p>
 */
// todo: is there a way to split this out into two interfaces?
public class DefaultDifferencerInternals implements IDifferencerInternals {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffRecurse(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual) {
        for (Field field : config.getReflection().fields(expected)) {
            final String path = prefixPath + "." + field.getName();

            final Object expectedFieldValue = config.getReflection().fieldTo(field, expected);
            final Object actualFieldValue = config.getReflection().fieldTo(field, actual);

            diffObjects(config, deltas, path, expectedFieldValue, actualFieldValue);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffObjects(final DifferencerConfiguration config, final List<Delta> deltas, final String path, final T expected, final T actual) {
        final ICollectionHandler ch = config.getCollectionHandler();
        final IMapHandler mh = config.getMapHandler();
        final IEnumHandler eh = config.getEnumHandler();
        final INullHandler nh = config.getNullHandler();

        if (expected == null || actual == null) {
            nh.handleNulls(config, path, deltas, expected, actual);
        } else if (eh.isEnum(expected, actual)) {
            if (!config.getEqualsTester().test(expected, actual)) {
                deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
            }
        } else if (ch.isCollection(expected, actual)) {
            ch.diffCollections(config, deltas, path, ch.findCollection(expected), ch.findCollection(actual));
        } else if (mh.isMap(expected, actual)) {
            mh.diffMaps(config, deltas, path, mh.findAllElements(expected), mh.findAllElements(actual));
        } else if (config.getShouldRecurse().test(config, expected, actual)) {
            diffRecurse(config, deltas, path, expected, actual);
        } else if (!config.getEqualsTester().test(expected, actual)) {
            deltas.add(Delta.from(config, DeltaType.NOT_EQUAL, path, expected, actual));
        }
    }
}
