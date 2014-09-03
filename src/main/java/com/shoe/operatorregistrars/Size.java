package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

import java.math.BigDecimal;

public class Size implements OperatorRegistrar {
    @Override
    public Operator get() {
        return stack -> stack.push(BigDecimal.valueOf(stack.size()));
    }
}
