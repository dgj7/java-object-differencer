package io.dgj7.jod.testonly.model.business.comp;

import io.dgj7.jod.testonly.model.business.def.Title;
import lombok.Getter;
import lombok.Setter;

public class Employee extends Person {
    @Getter
    @Setter
    private String employeeId;
    @Getter
    @Setter
    private Title title;

    @Override
    public String toString() {
        return "Employee[" + title + ": " + employeeId + "]";
    }
}
