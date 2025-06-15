package io.dgj7.jod.e2e.builtin.types;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StringEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testExpectedNull() {
        final List<Delta> results = differencer.difference(null, "test");

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.lang.String (java.lang.String): expected=[null], actual=[test]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final List<Delta> results = differencer.difference("test", null);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.lang.String (java.lang.String): expected=[test], actual=[null]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        final List<Delta> results = differencer.difference("test", "f");

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NOT_EQUAL: java.lang.String (java.lang.String): expected=[test], actual=[f]", results.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final List<Delta> results = differencer.difference("test", "test");

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}
