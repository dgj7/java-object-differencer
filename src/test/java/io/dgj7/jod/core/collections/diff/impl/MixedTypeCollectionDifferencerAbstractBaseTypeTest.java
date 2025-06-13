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
 * Test {@link MixedTypeCollectionDifferencer} with a list of an abstract base type.
 */
public class MixedTypeCollectionDifferencerAbstractBaseTypeTest {
    private static final String PATH = List.class.getName();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();
    private final ICollectionDifferencer objectUnderTest = new MixedTypeCollectionDifferencer(new HashMap<>(), Object::equals);

    @Test
    public final void testEmptyLists() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of();
        final List<Number> actual = List.of();

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }

    @Test
    public final void testExpectedLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of(3, 4, 5, 6);
        final List<Number> actual = List.of(4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(1, deltas.size());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[0] (java.lang.Integer): no matching element for [3]", deltas.get(0).toString());
    }

    @Test
    public final void testExpectedLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of(4, 5, 6, 7);
        final List<Number> actual = List.of(4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(1, deltas.size());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[3] (java.lang.Integer): no matching element for [7]", deltas.get(0).toString());
    }

    // todo: fix this
    @Test
    public final void testActualLargerLeft() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of(4, 5, 6);
        final List<Number> actual = List.of(3, 4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }

    // todo: fix this
    @Test
    public final void testActualLargerRight() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of(4, 5, 6);
        final List<Number> actual = List.of(4, 5, 6, 7);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }

    @Test
    public final void testSameSizeNotEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of(4, 5, 6);
        final List<Number> actual = List.of(4, 5, 7.1);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(1, deltas.size());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.List[2] (java.lang.Integer): no matching element for [6]", deltas.get(0).toString());
    }

    @Test
    public final void testSameSizeEqual() {
        final List<Delta> deltas = new LinkedList<>();

        final List<Number> expected = List.of(4, 5, 6);
        final List<Number> actual = List.of(4, 5, 6);

        objectUnderTest.diffCollections(config, deltas, PATH, expected, actual);

        Assert.assertEquals(0, deltas.size());
    }
}
