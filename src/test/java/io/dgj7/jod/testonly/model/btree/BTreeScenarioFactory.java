package io.dgj7.jod.testonly.model.btree;

import io.dgj7.jod.testonly.model.ScenarioVersion;

public class BTreeScenarioFactory {
    public BTree<Integer> integerScenario(final ScenarioVersion version) {
        final BTree<Integer> scenario = new BTree<>();
        scenario.add(5);
        scenario.add(9);
        scenario.add(3);
        if (ScenarioVersion.EXPECTED.equals(version)) {
            scenario.add(7);
        } else {
            scenario.add(8);
        }
        scenario.add(10);
        scenario.add(23);
        return scenario;
    }
}
