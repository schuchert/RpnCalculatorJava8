package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

public class Drop implements OperatorRegistrar {
    @Override
    public Operator get() {
        return values -> {
            values.pop();
        };
    }
}
