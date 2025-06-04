package io.dgj7.jod.testonly.model.mixed;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class DerivedIntegerType extends AbstractBaseType {
    @Getter
    private final Integer derivedIntegerValue;

    public DerivedIntegerType(final String pBaseTypeValue, final Integer pDerivedIntegerValue) {
        super(pBaseTypeValue);
        this.derivedIntegerValue = Objects.requireNonNull(pDerivedIntegerValue, "DerivedIntegerValue(Integer) is null");
    }
}
