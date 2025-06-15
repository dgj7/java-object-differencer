package io.dgj7.jod.e2e.builtin.primitive;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BooleanEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testExpectedNull() {
        final List<Delta> results = differencer.difference(null, true);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.lang.Boolean (java.lang.Boolean): expected=[null], actual=[true]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final List<Delta> results = differencer.difference(false, null);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.lang.Boolean (java.lang.Boolean): expected=[false], actual=[null]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        final List<Delta> results = differencer.difference(false, true);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NOT_EQUAL: java.lang.Boolean (java.lang.Boolean): expected=[false], actual=[true]", results.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final List<Delta> results = differencer.difference(false, false);

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}
