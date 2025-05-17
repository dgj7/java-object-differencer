package io.dgj7.jod.testonly.model.btree.set;

import io.dgj7.jod.testonly.model.ScenarioVersion;

public class BTreeSortedSetScenarioFactory {
    public static BTreeSortedSet<Integer> integerScenario(final ScenarioVersion version) {
        final BTreeSortedSet<Integer> scenario = new BTreeSortedSet<>();
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

    public static BTreeSortedSet<String> stringScenario(final ScenarioVersion version) {
        final BTreeSortedSet<String> scenario = new BTreeSortedSet<>();
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
