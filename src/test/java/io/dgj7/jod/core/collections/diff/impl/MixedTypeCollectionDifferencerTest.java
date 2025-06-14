package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Test {@link MixedTypeCollectionDifferencer}.
 */
public class MixedTypeCollectionDifferencerTest {
    private static final String PATH = List.class.getName();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();
    private final ICollectionDifferencer objectUnderTest = new MixedTypeCollectionDifferencer(new HashMap<>(), Object::equals);

    @Test
    public final void testEmptyLists() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of();
        final List<Integer> actual = List.of();

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }

    @Test
    public final void testExpectedLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of(0, 1, 2, 3);
        final List<Integer> actual = List.of(1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[0] (java.lang.Integer): no matching element for expected=[0]", deltas.get(1).toString());
    }

    @Test
    public final void testExpectedLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of(1, 2, 3, 4);
        final List<Integer> actual = List.of(1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for expected=[4]", deltas.get(1).toString());
    }

    @Test
    public final void testActualLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = List.of(0, 1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[0]", deltas.get(1).toString());
    }

    @Test
    public final void testActualLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = List.of(1, 2, 3, 4);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[4]", deltas.get(1).toString());
    }

    @Test
    public final void testSameSizeNotEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = List.of(1, 2, 4);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[2] (java.lang.Integer): no matching element for expected=[3]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[4]", deltas.get(1).toString());
    }

    @Test
    public final void testSameSizeEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = List.of(1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }
}
