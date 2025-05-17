package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.model.business.comp.Customer;

public class NextCustomer extends AbstractNextPerson<Customer> {
    private static NextCustomer INSTANCE;
    private static final boolean START = false;

    private boolean firstTimeCustomer;

    private NextCustomer() {
        firstTimeCustomer = START;
    }

    public static NextCustomer instance() {
        if (INSTANCE == null) {
            synchronized (NextCustomer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NextCustomer();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Customer next() {
        final Customer customer = new Customer();

        firstTimeCustomer = !firstTimeCustomer;

        customer.setFirstTimeCustomer(firstTimeCustomer);
        populate(customer);

        return customer;
    }

    @Override
    public void reset() {
        firstTimeCustomer = START;
    }
}
