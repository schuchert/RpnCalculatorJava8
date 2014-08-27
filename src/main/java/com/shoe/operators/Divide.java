package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

public class Divide implements OperatorRegistrar {
    public String name() {
        return "/";
    }

    @Override
    public Operator get() {
        return BinaryOperatorBuilder.build((lhs, rhs) -> lhs.divide(rhs));
    }
}
