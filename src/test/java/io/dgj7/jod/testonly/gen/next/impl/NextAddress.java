package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.gen.next.Next;
import io.dgj7.jod.testonly.model.business.comp.Address;

public class NextAddress extends Next<Address> {
    private static NextAddress INSTANCE;
    private static final int ADDR_NUM_START = 1000;

    private int addrNum = ADDR_NUM_START;

    private NextAddress() {
        // purposely empty
    }

    public static NextAddress instance() {
        if (INSTANCE == null) {
            synchronized (NextAddress.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NextAddress();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Address next() {
        final Address address = new Address();

        address.setLine1(addrNum + " some st");
        address.setCity("boston");
        address.setState("MA");
        address.setPostalCode("02117");

        return address;
    }

    @Override
    public void reset() {
        addrNum = ADDR_NUM_START;
    }
}
