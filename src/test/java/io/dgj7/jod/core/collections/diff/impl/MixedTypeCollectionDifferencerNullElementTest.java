package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Test {@link MixedTypeCollectionDifferencer} with nulls in the input list.
 */
public class MixedTypeCollectionDifferencerNullElementTest {
    private static final String PATH = List.class.getName();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();
    private final ICollectionDifferencer objectUnderTest = new MixedTypeCollectionDifferencer(new HashMap<>(), Object::equals);

    @Test
    public final void testExpectedNullBefore() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(null, 7, 8, 9);
        final List<Integer> actual = makeList(7, 8, 9);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[0] (java.lang.String): no matching element for expected=[null]", deltas.get(1).toString());
    }

    @Test
    public final void testExpectedNullAfter() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(7, 8, 9, null);
        final List<Integer> actual = makeList(7, 8, 9);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.String): no matching element for expected=[null]", deltas.get(1).toString());
    }

    @Test
    public final void testActualNullBefore() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(7, 8, 9);
        final List<Integer> actual = makeList(null, 7, 8, 9);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (unknown): extra unmatched element; actual=[null]", deltas.get(1).toString());
    }

    @Test
    public final void testActualNullAfter() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(7, 8, 9);
        final List<Integer> actual = makeList(7, 8, 9, null);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (unknown): extra unmatched element; actual=[null]", deltas.get(1).toString());
    }
    
    private List<Integer> makeList(final Integer ... elements) {
        Objects.requireNonNull(elements, "Integer[] is null");
        return new LinkedList<>(Arrays.asList(elements));
    }
}
