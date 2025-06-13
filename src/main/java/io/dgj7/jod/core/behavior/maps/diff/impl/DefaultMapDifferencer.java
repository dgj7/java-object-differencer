package io.dgj7.jod.core.behavior.maps.diff.impl;

import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.behavior.maps.diff.IMapDifferencer;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.List;
import java.util.Map;

/**
 * Default {@link IMapDifferencer}.
 */
public class DefaultMapDifferencer implements IMapDifferencer {
    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> void diffMaps(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Map<K, V> expectedMap, final Map<K, V> actualMap) {
        final IObjectDifferencer od = config.getObjectDifferencer();

        if (expectedMap.size() == actualMap.size()) {
            for (K key : expectedMap.keySet()) {
                final V expected = expectedMap.get(key);
                final V actual = actualMap.get(key);
                final String path = prefixPath + "[" + key + "]";
                od.diffObjects(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(Delta.from(config, DeltaType.MAP_SIZES_NOT_EQUAL, prefixPath, expectedMap.size(), actualMap.size()));
        }
    }
}
