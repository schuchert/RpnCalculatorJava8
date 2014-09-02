package com.shoe.com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

import java.math.BigDecimal;

public class LessThan implements OperatorRegistrar {
    public String name() {
        return "<";
    }

    @Override
    public Operator get() {
        return BinaryOperatorBuilder.build((lhs, rhs) -> lhs.compareTo(rhs) < 0 ? BigDecimal.ONE : BigDecimal.ZERO);
    }
}
