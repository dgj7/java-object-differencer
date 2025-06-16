package io.dgj7.jod.e2e.mixed;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.mixed.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class MixedEntityEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testNullExpected() {
        final MixedListOwnerType expected = null;
        final MixedListOwnerType actual = MixedFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.mixed.MixedListOwnerType (io.dgj7.jod.testonly.model.mixed.MixedListOwnerType): expected=[null], actual=[MixedListOwnerType(values=[DerivedIntegerType(derivedIntegerValue=1), DerivedDoubleType(derivedDoubleValue=1.1), DerivedStringType(derivedStringValue=str)])]", diffs.get(0).toString());
    }

    @Test
    public final void testNullActual() {
        final MixedListOwnerType expected = MixedFactory.create(ScenarioVersion.EXPECTED);
        final MixedListOwnerType actual = null;

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.mixed.MixedListOwnerType (io.dgj7.jod.testonly.model.mixed.MixedListOwnerType): expected=[MixedListOwnerType(values=[DerivedIntegerType(derivedIntegerValue=1), DerivedDoubleType(derivedDoubleValue=1.1), DerivedStringType(derivedStringValue=str)])], actual=[null]", diffs.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        final MixedListOwnerType expected = MixedFactory.create(ScenarioVersion.EXPECTED);
        final MixedListOwnerType actual = MixedFactory.create(ScenarioVersion.ACTUAL);

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.mixed.MixedListOwnerType.values[0].derivedIntegerValue (java.lang.Integer): expected=[1], actual=[2]", diffs.get(0).toString());
    }

    @Test
    public final void testEqualDifferentOrderDefaultConfiguration() {
        final MixedListOwnerType expected = MixedFactory.create(ScenarioVersion.EXPECTED);
        final MixedListOwnerType actual = MixedFactory.create(ScenarioVersion.EXPECTED, true);

        // todo: is there a way around this with default diff?
        Assert.assertThrows(IllegalArgumentException.class, () -> differencer.difference(expected, actual));
    }

    @Test
    public final void testEqualSameOrder() {
        final MixedListOwnerType expected = MixedFactory.create(ScenarioVersion.EXPECTED);
        final MixedListOwnerType actual = MixedFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertEquals(0, diffs.size());
    }
}
