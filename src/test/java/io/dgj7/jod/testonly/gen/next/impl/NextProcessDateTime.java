package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.gen.next.Next;

import java.time.LocalDateTime;
import java.time.Month;

public class NextProcessDateTime extends Next<LocalDateTime> {
    private static NextProcessDateTime INSTANCE;

    private static final LocalDateTime START = LocalDateTime.of(2022, Month.JULY, 7, 3, 47);
    private static final int INCREMENT_MINUTES = 26;

    private LocalDateTime current;

    private NextProcessDateTime() {
        current = START;
    }

    public static NextProcessDateTime instance() {
        if (INSTANCE == null) {
            synchronized (NextProcessDateTime.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NextProcessDateTime();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LocalDateTime next() {
        current = current.plusMinutes(INCREMENT_MINUTES);
        return current;
    }

    @Override
    public void reset() {
        current = START;
    }
}
