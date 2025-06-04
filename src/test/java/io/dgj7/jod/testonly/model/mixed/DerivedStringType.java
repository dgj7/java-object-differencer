package io.dgj7.jod.testonly.model.mixed;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class DerivedStringType extends AbstractBaseType {
    @Getter
    private final String derivedStringValue;

    public DerivedStringType(final String pBaseValue, final String pDerivedStringValue) {
        super(pBaseValue);
        this.derivedStringValue = Objects.requireNonNull(pDerivedStringValue, "DerivedStringValue(String) is null");
    }
}
