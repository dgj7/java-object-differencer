package io.dgj7.jod.testonly.model.business.comp;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Order {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Widget widget;
    @Getter
    @Setter
    private Customer customer;

    @Getter
    @Setter
    private LocalDateTime placed;
    @Getter
    @Setter
    private LocalDateTime packaged;
    @Getter
    @Setter
    private LocalDateTime shipped;
}
