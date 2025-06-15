package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Test {@link DefaultCollectionDifferencer} with nulls in the input list.
 */
public class DefaultCollectionDifferencerNullElementTest {
    private static final String PATH = List.class.getName();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();
    private final ICollectionDifferencer objectUnderTest = new DefaultCollectionDifferencer();

    @Test
    public final void testExpectedNullBefore() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(null, 7, 8, 9);
        final List<Integer> actual = makeList(7, 8, 9);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(5, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NULLITY: java.util.List[0] (java.lang.Integer): expected=[null], actual=[7]", deltas.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[1] (java.lang.Integer): expected=[7], actual=[8]", deltas.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[8], actual=[9]", deltas.get(3).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for expected=[9]", deltas.get(4).toString());
    }

    @Test
    public final void testExpectedNullAfter() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(7, 8, 9, null);
        final List<Integer> actual = makeList(7, 8, 9);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3]: no matching element for expected=[null]", deltas.get(1).toString());
    }

    @Test
    public final void testActualNullBefore() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(7, 8, 9);
        final List<Integer> actual = makeList(null, 7, 8, 9);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(5, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("NULLITY: java.util.List[0] (java.lang.Integer): expected=[7], actual=[null]", deltas.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[1] (java.lang.Integer): expected=[8], actual=[7]", deltas.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[9], actual=[8]", deltas.get(3).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[9]", deltas.get(4).toString());
    }

    @Test
    public final void testActualNullAfter() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = makeList(7, 8, 9);
        final List<Integer> actual = makeList(7, 8, 9, null);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1]: extra unmatched element; actual=[null]", deltas.get(1).toString());
    }

    private List<Integer> makeList(final Integer ... elements) {
        Objects.requireNonNull(elements, "Integer[] is null");
        return new LinkedList<>(Arrays.asList(elements));
    }
}
