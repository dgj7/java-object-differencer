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
 * Test {@link DefaultCollectionDifferencer} with a list of an abstract base type.
 */
public class DefaultCollectionDifferencerAbstractBaseTypeTest {
    private static final String PATH = List.class.getName();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();
    private final ICollectionDifferencer objectUnderTest = new DefaultCollectionDifferencer();

    @Test
    public final void testEmptyLists() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList();
        final List<Number> actual = Arrays.asList();

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }

    @Test
    public final void testExpectedLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList(3, 4, 5, 6);
        final List<Number> actual = Arrays.asList(4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(5, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[0] (java.lang.Integer): expected=[3], actual=[4]", deltas.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[1] (java.lang.Integer): expected=[4], actual=[5]", deltas.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[5], actual=[6]", deltas.get(3).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for expected=[6]", deltas.get(4).toString());
    }

    @Test
    public final void testExpectedLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList(4, 5, 6, 7);
        final List<Number> actual = Arrays.asList(4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[4], actual=[3]", deltas.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for expected=[7]", deltas.get(1).toString());
    }

    @Test
    public final void testActualLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList(4, 5, 6);
        final List<Number> actual = Arrays.asList(3, 4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(5, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[0] (java.lang.Integer): expected=[4], actual=[3]", deltas.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[1] (java.lang.Integer): expected=[5], actual=[4]", deltas.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer): expected=[6], actual=[5]", deltas.get(3).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[6]", deltas.get(4).toString());
    }

    @Test
    public final void testActualLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList(4, 5, 6);
        final List<Number> actual = Arrays.asList(4, 5, 6, 7);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(2, deltas.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.List (java.lang.Integer): expected=[3], actual=[4]", deltas.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.List[3+1] (java.lang.Integer): extra unmatched element; actual=[7]", deltas.get(1).toString());
    }

    @Test
    public final void testSameSizeNotEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList(4, 5, 6);
        final List<Number> actual = Arrays.asList(4, 5, 7.1);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(1, deltas.size());
        Assert.assertEquals("NOT_EQUAL: java.util.List[2] (java.lang.Integer/java.lang.Double): expected=[6], actual=[7.1]", deltas.get(0).toString());
    }

    @Test
    public final void testSameSizeEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = Arrays.asList(4, 5, 6);
        final List<Number> actual = Arrays.asList(4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }
}
