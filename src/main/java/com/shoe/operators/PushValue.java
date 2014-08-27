package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;

import java.math.BigDecimal;

public class PushValue implements Operator {
    private BigDecimal value;

    public PushValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public void accept(RpnStack values) {
        values.push(value);
    }
}
