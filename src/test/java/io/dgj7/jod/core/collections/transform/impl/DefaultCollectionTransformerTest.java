package io.dgj7.jod.core.collections.transform.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.transform.ICollectionTransformer;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Test {@link DefaultCollectionTransformer}.
 */
public class DefaultCollectionTransformerTest {
    private static final DifferencerConfiguration CONFIG = DifferencerConfiguration.builder()
            .build();

    private final ICollectionTransformer objectUnderTest = new DefaultCollectionTransformer();

    @Test
    public final void testNullInput() {
        final SortedSet<Double> input = null;

        final Collection<Integer> output = objectUnderTest.objectToCollection(CONFIG, input);

        Assert.assertNull(output);
    }

    @Test
    public final void testNotCollectionButObject() {
        final Integer input = 4;

        Assert.assertThrows(ClassCastException.class, () -> objectUnderTest.objectToCollection(CONFIG, input));
    }

    @Test
    public final void testNotCollectionButMap() {
        final Map<String, String> input = new HashMap<>();

        Assert.assertThrows(ClassCastException.class, () -> objectUnderTest.objectToCollection(CONFIG, input));
    }

    @Test
    public final void testEmptyCollection() {
        final SortedSet<Double> input = new TreeSet<>();

        final Collection<Integer> output = objectUnderTest.objectToCollection(CONFIG, input);

        Assert.assertNotNull(output);
        Assert.assertEquals(0, output.size());
    }

    @Test
    public final void testPopulatedCollection() {
        final List<Integer> input = Arrays.asList(1, 2, 3);

        final Collection<Integer> output = objectUnderTest.objectToCollection(CONFIG, input);

        Assert.assertNotNull(output);
        Assert.assertEquals(3, output.size());
        final Iterator<Integer> iterator = output.iterator();
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
