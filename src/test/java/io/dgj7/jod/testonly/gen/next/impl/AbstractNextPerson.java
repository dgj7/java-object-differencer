package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.gen.next.Next;
import io.dgj7.jod.testonly.model.business.comp.Person;

abstract class AbstractNextPerson<P extends Person> extends Next<P> {
    protected void populate(final P person) {
        person.setFirstName("james");
        person.setMiddleName("herbert");
        person.setLastName("keenan");
    }
}
