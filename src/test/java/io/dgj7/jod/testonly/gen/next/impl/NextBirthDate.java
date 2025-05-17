package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.gen.next.Next;

import java.time.LocalDate;
import java.time.Month;

public class NextBirthDate extends Next<LocalDate> {
    private static NextBirthDate INSTANCE;
    private static final LocalDate START = LocalDate.of(1983, Month.JANUARY, 24);

    private LocalDate current = START;

    private NextBirthDate() {
        // purposely empty
    }

    public static NextBirthDate instance() {
        if (INSTANCE == null) {
            synchronized (NextBirthDate.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NextBirthDate();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LocalDate next() {
        current = current.plusDays(3);
        return current;
    }

    @Override
    public void reset() {
        current = START;
    }
}
