package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Test {@link DefaultCollectionDifferencer}.
 */
public class DefaultCollectionDifferencerTest {
    private static final String PATH = List.class.getName();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();
    private final ICollectionDifferencer objectUnderTest = new DefaultCollectionDifferencer();

    @Test
    public final void testEmptyLists() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList();
        final List<Integer> actual = Arrays.asList();

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }

    @Test
    public final void testExpectedLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList(0, 1, 2, 3);
        final List<Integer> actual = Arrays.asList(1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(5, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[0] (java.lang.Integer): expected=[0], actual=[1]", deltas.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[1] (java.lang.Integer): expected=[1], actual=[2]", deltas.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[2], actual=[3]", deltas.get(3).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for expected=[3]", deltas.get(4).toString());
    }

    @Test
    public final void testExpectedLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        final List<Integer> actual = Arrays.asList(1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for expected=[4]", deltas.get(1).toString());
    }

    @Test
    public final void testActualLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        final List<Integer> actual = Arrays.asList(0, 1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(5, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[0] (java.lang.Integer): expected=[1], actual=[0]", deltas.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[1] (java.lang.Integer): expected=[2], actual=[1]", deltas.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[3], actual=[2]", deltas.get(3).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[3]", deltas.get(4).toString());
    }

    @Test
    public final void testActualLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        final List<Integer> actual = Arrays.asList(1, 2, 3, 4);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[4]", deltas.get(1).toString());
    }

    @Test
    public final void testSameSizeNotEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        final List<Integer> actual = Arrays.asList(1, 2, 4);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(1, deltas.size());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
    }

    @Test
    public final void testSameSizeEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        final List<Integer> actual = Arrays.asList(1, 2, 3);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }
}
