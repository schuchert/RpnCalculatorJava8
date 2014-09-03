package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

public class Divide implements OperatorRegistrar {
    @Override
    public Operator get() {
        return BinaryOperatorBuilder.build((lhs, rhs) -> lhs.divide(rhs));
    }
}
