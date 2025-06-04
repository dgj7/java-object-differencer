package io.dgj7.jod.testonly.model.mixed;

import io.dgj7.jod.testonly.model.ScenarioVersion;
import org.junit.Assert;

import java.util.List;

public class MixedFactory {
    public static MixedListOwnerType create(final ScenarioVersion version, final boolean alternateOrder) {
        final MixedListOwnerType output = create(version);
        if (alternateOrder) {
            final List<AbstractBaseType> copy = List.copyOf(output.getValues());
            output.getValues().clear();
            Assert.assertEquals(3, copy.size());
            output.getValues().add(copy.get(1));
            output.getValues().add(copy.get(2));
            output.getValues().add(copy.get(0));
            Assert.assertEquals(3, output.getValues().size());
        }
        return output;
    }

    public static MixedListOwnerType create(final ScenarioVersion version) {
        final MixedListOwnerType output = new MixedListOwnerType();
        if (ScenarioVersion.EXPECTED.equals(version)) {
            output.getValues().add(new DerivedIntegerType("base:1", 1));
            output.getValues().add(new DerivedDoubleType("base:1.1", 1.1));
            output.getValues().add(new DerivedStringType("base:str", "str"));
        } else {
            output.getValues().add(new DerivedIntegerType("base:1", 2));
            output.getValues().add(new DerivedDoubleType("base:1.1", 1.1));
            output.getValues().add(new DerivedStringType("base:str", "str"));
        }
        return output;
    }
}
