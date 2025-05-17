package io.dgj7.jod.e2e.builtin;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InaccessibleObjectException;
import java.math.BigDecimal;
import java.util.List;

public class BigDecimalEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testExpectedNull() {
        final List<Delta> results = difference(null, BigDecimal.valueOf(0.5));

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.math.BigDecimal (java.math.BigDecimal): expected=[null], actual=[0.5]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final List<Delta> results = difference(BigDecimal.valueOf(0.75), null);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.math.BigDecimal (java.math.BigDecimal): expected=[0.75], actual=[null]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        // todo: add-opens on java.math
        Assert.assertThrows("fail", InaccessibleObjectException.class, () -> difference(BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.5)));
    }

    @Test
    public final void testEqual() {
        // todo: add-opens on java.math
        Assert.assertThrows("fail", InaccessibleObjectException.class, () -> difference(BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.75)));
    }
}
