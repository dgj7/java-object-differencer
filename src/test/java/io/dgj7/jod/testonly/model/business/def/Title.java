package io.dgj7.jod.testonly.model.business.def;

import io.dgj7.jod.testonly.model.ScenarioVersion;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

public enum Title {
    EXECUTIVE(3),
    DIRECTOR(9),
    MANAGER(18),
    PEON(100),
    PEASANT(100),
    ;

    @Getter
    private final int employeesPerTitle;

    Title(final Integer pEmployeesPerTitle) {
        this.employeesPerTitle = Objects.requireNonNull(pEmployeesPerTitle);
    }

    public static List<Title> titles(final ScenarioVersion version) {
        if (ScenarioVersion.EXPECTED.equals(version)) {
            return List.of(EXECUTIVE, DIRECTOR, MANAGER, PEON);
        } else if (ScenarioVersion.ACTUAL.equals(version)) {
            return List.of(EXECUTIVE, DIRECTOR, MANAGER, PEASANT);
        } else {
            throw new IllegalArgumentException("unknown version: " + version);
        }
    }
}
