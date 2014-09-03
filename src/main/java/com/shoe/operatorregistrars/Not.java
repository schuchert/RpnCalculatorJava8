package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

import java.math.BigDecimal;

public class Not implements OperatorRegistrar {
    @Override
    public Operator get() {
        return values -> values.push(values.pop().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ONE : BigDecimal.ZERO);
    }
}
