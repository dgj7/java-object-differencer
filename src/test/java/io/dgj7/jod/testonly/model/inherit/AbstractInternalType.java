package io.dgj7.jod.testonly.model.inherit;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractInternalType extends AbstractBaseType {
    @Getter
    @Setter
    private Integer internalValue;
}
