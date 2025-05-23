package io.dgj7.jod.testonly.model.binarytree.set;

import io.dgj7.jod.testonly.model.ScenarioVersion;

public class BinaryTreeSortedSetScenarioFactory {
    public static BinaryTreeSortedSet<Integer> integerScenario(final ScenarioVersion version) {
        final BinaryTreeSortedSet<Integer> scenario = new BinaryTreeSortedSet<>();
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

    public static BinaryTreeSortedSet<String> stringScenario(final ScenarioVersion version) {
        final BinaryTreeSortedSet<String> scenario = new BinaryTreeSortedSet<>();
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
