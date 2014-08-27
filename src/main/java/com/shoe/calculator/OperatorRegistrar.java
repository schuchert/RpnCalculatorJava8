package com.shoe.calculator;

import java.beans.Introspector;

public interface OperatorRegistrar {
    Operator get();

    default String name() {
        return Introspector.decapitalize(getClass().getSimpleName());
    }
}
