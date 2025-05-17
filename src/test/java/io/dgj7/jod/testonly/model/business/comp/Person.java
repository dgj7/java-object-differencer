package io.dgj7.jod.testonly.model.business.comp;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public abstract class Person {
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String middleName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private LocalDate birthDate;
    @Getter
    @Setter
    private Address homeAddress;
}
