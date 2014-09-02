package com.shoe.com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

import java.math.BigDecimal;

public class Swap implements OperatorRegistrar {
    @Override
    public Operator get() {
        return values -> {
            BigDecimal originalTop = values.pop();
            BigDecimal originalSecondValue = values.pop();

            values.push(originalTop);
            values.push(originalSecondValue);
        };
    }
}
