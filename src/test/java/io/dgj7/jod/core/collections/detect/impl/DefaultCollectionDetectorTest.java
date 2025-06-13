package io.dgj7.jod.core.collections.detect.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.detect.ICollectionDetector;
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
        final DifferencerConfiguration config = DifferencerConfiguration.builder()
                .build();

        final List<Integer> expected = null;
        final List<Integer> actual = List.of(4, 5, 6);

        Assert.assertTrue(objectUnderTest.isCollection(config, expected, actual));
    }

    @Test
    public final void testNullActual() {
        final DifferencerConfiguration config = DifferencerConfiguration.builder()
                .build();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = null;

        Assert.assertTrue(objectUnderTest.isCollection(config, expected, actual));
    }

    @Test
    public final void testBothNull() {
        final DifferencerConfiguration config = DifferencerConfiguration.builder()
                .build();

        final List<Integer> expected = null;
        final List<Integer> actual = null;

        Assert.assertFalse(objectUnderTest.isCollection(config, expected, actual));
    }

    @Test
    public final void testNotCollection() {
        final DifferencerConfiguration config = DifferencerConfiguration.builder()
                .build();

        final String expected = "expected";
        final String actual = "actual";

        Assert.assertFalse(objectUnderTest.isCollection(config, expected, actual));
    }

    @Test
    public final void testCollection() {
        final DifferencerConfiguration config = DifferencerConfiguration.builder()
                .build();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = List.of(4, 5, 6);

        Assert.assertTrue(objectUnderTest.isCollection(config, expected, actual));
    }
}
