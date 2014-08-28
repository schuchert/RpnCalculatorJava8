package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

public class OperatorFactoryTest {
    private OperatorFactory factory;

    @Before
    public void init() {
        factory = new OperatorFactory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForUnknownOperator() {
        factory.operatorNamed("@@@shouldnotbefound%%%");
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void shouldNotallowRegistrationOfSameOperatorName() {
        factory.register("add", values-> {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireOperatorNameBeNonNull() {
        factory.register(null, values-> {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireOperatorNameBeAtLeast1CharacterLong() {
        factory.register("", values-> {});

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireOperatorToBeNonNull() {
        factory.register("okName", null);
    }

}
