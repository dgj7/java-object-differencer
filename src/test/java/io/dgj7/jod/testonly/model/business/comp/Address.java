package io.dgj7.jod.testonly.model.business.comp;

import lombok.Getter;
import lombok.Setter;

public class Address {
    @Getter
    @Setter
    private String line1;
    @Getter
    @Setter
    private String line2;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String state;
    @Getter
    @Setter
    private String postalCode;
}
