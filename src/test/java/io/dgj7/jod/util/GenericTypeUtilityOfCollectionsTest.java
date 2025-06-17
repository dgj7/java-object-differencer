package io.dgj7.jod.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class GenericTypeUtilityOfCollectionsTest {
    @Test
    public final void testEmptyLists() {
        final Collection<Integer> expected = List.of();
        final Collection<Integer> actual = List.of();

        final Class<?> result = GenericTypeUtility.ofCollections(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(Object.class, result);
    }

    @Test
    public final void testIntegers() {
        final Collection<Integer> expected = List.of(1, 2, 3);
        final Collection<Integer> actual = List.of(4, 5, 6);

        final Class<?> result = GenericTypeUtility.ofCollections(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.class, result);
    }

    @Test
    @SuppressWarnings("UnnecessaryBoxing")
    public final void testNumbers() {
        final Collection<Number> expected = List.of(Integer.valueOf(1), Double.valueOf(3.14159), Float.valueOf(4.27F));
        final Collection<Number> actual = List.of();

        final Class<?> result = GenericTypeUtility.ofCollections(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(Number.class, result);
    }
}
