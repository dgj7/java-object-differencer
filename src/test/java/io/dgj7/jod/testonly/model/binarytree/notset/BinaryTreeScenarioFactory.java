package io.dgj7.jod.testonly.model.binarytree.notset;

import io.dgj7.jod.testonly.model.ScenarioVersion;

public class BinaryTreeScenarioFactory {
    public static BinaryTree<Integer> integerScenario(final ScenarioVersion version) {
        final BinaryTree<Integer> scenario = new BinaryTree<>();
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

    public static BinaryTree<String> stringScenario(final ScenarioVersion version) {
        final BinaryTree<String> scenario = new BinaryTree<>();
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
