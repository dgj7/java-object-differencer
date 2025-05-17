package io.dgj7.jod.testonly.model.btree.notset;

import io.dgj7.jod.testonly.model.ScenarioVersion;

public class BTreeScenarioFactory {
    public static BTree<Integer> integerScenario(final ScenarioVersion version) {
        final BTree<Integer> scenario = new BTree<>();
        scenario.add(31);
        scenario.add(15);
        scenario.add(47);
        if (ScenarioVersion.EXPECTED.equals(version)) {
            scenario.add(7);
        } else {
            scenario.add(8);
        }
        scenario.add(23);
        scenario.add(38);
        scenario.add(52);
        return scenario;
    }

    public static BTree<String> stringScenario(final ScenarioVersion version) {
        final BTree<String> scenario = new BTree<>();
        scenario.add("31");
        scenario.add("15");
        scenario.add("47");
        if (ScenarioVersion.EXPECTED.equals(version)) {
            scenario.add("7");
        } else {
            scenario.add("8");
        }
        scenario.add("23");
        scenario.add("38");
        scenario.add("52");
        return scenario;
    }
}
