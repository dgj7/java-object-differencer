package io.dgj7.jod.testonly.model.inherit;

import io.dgj7.jod.testonly.model.ScenarioVersion;

public class InheritedTypeFactory {
    public static LeafType create(final ScenarioVersion version) {
        final LeafType output = new LeafType();

        output.setBaseValue("base");
        output.setInternalValue(1);
        output.setLeafValue("leaf");

        if (ScenarioVersion.ACTUAL.equals(version)) {
            output.setBaseValue("modified");
        }

        return output;
    }
}
