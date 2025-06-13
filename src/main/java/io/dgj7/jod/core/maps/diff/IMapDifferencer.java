package io.dgj7.jod.core.maps.diff;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Handle maps.
 * </p>
 */
public interface IMapDifferencer {
    /**
     * Diff maps.
     */
    <K, V> void diffMaps(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Map<K, V> expectedMap, final Map<K, V> actualMap);
}
