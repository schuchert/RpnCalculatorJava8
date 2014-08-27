package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

public class Clear implements OperatorRegistrar {
    public Operator get() {
        return values -> values.clear();
    }
}
