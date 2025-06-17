package io.dgj7.jod.core.collections.detect.impl;

import io.dgj7.jod.xt.collections.detect.ICollectionDetector;
import io.dgj7.jod.xt.collections.detect.impl.DefaultCollectionDetector;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test {@link DefaultCollectionDetector}.
 */
public class DefaultCollectionDetectorTest {
    private final ICollectionDetector objectUnderTest = new DefaultCollectionDetector();

    @Test
    public final void testNullExpected() {
        final List<Integer> expected = null;
        final List<Integer> actual = List.of(4, 5, 6);

        Assert.assertTrue(objectUnderTest.isCollection(expected, actual));
    }

    @Test
    public final void testNullActual() {
        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = null;

        Assert.assertTrue(objectUnderTest.isCollection(expected, actual));
    }

    @Test
    public final void testBothNull() {
        final List<Integer> expected = null;
        final List<Integer> actual = null;

        Assert.assertFalse(objectUnderTest.isCollection(expected, actual));
    }

    @Test
    public final void testNotCollection() {
        final String expected = "expected";
        final String actual = "actual";

        Assert.assertFalse(objectUnderTest.isCollection(expected, actual));
    }

    @Test
    public final void testCollection() {
        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = List.of(4, 5, 6);

        Assert.assertTrue(objectUnderTest.isCollection(expected, actual));
    }
}
