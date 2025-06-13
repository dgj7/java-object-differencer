package io.dgj7.jod.e2e.mixed;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.core.behavior.collections.diff.impl.MixedTypeCollectionDifferencer;
import io.dgj7.jod.model.config.DifferencerConfiguration;
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

public class MixedEndToEndTest {
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

        Assert.assertThrows(IllegalArgumentException.class, () -> differencer.difference(expected, actual));
    }

    @Test
    public final void testEqualDifferentOrderCustomCollectionHandler() {
        final Map<Class<?>, BiPredicate<?, ?>> map = new HashMap<>();
        map.put(DerivedDoubleType.class, (DerivedDoubleType x,DerivedDoubleType y) -> new EqualsBuilder()
                .append(x.getBaseTypeValue(), y.getBaseTypeValue())
                .append(x.getDerivedDoubleValue(), y.getDerivedDoubleValue())
                .isEquals());
        map.put(DerivedIntegerType.class, (DerivedIntegerType x,DerivedIntegerType y) -> new EqualsBuilder()
                .append(x.getBaseTypeValue(), y.getBaseTypeValue())
                .append(x.getDerivedIntegerValue(), y.getDerivedIntegerValue())
                .isEquals());
        map.put(DerivedStringType.class, (DerivedStringType x,DerivedStringType y) -> new EqualsBuilder()
                .append(x.getBaseTypeValue(), y.getBaseTypeValue())
                .append(x.getDerivedStringValue(), y.getDerivedStringValue())
                .isEquals());
        final DifferencerConfiguration config = DifferencerConfiguration.builder()
                .withCollectionDifferencer(new MixedTypeCollectionDifferencer(map, Object::equals))
                .build();

        final MixedListOwnerType expected = MixedFactory.create(ScenarioVersion.EXPECTED);
        final MixedListOwnerType actual = MixedFactory.create(ScenarioVersion.EXPECTED, true);

        final List<Delta> diffs = differencer.difference(config, expected, actual);

        Assert.assertEquals(0, diffs.size());
    }

    @Test
    public final void testEqualSameOrder() {
        final MixedListOwnerType expected = MixedFactory.create(ScenarioVersion.EXPECTED);
        final MixedListOwnerType actual = MixedFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertEquals(0, diffs.size());
    }
}
