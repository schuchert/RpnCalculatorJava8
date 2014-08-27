package com.shoe.calculator;

import java.math.BigDecimal;

public class Executing implements CalculatorState {
    private RpnStack values;
    private OperatorFactory factory;

    public Executing(RpnStack values, OperatorFactory factory) {
        this.values = values;
        this.factory = factory;
    }

    @Override
    public void enter(BigDecimal value) {
        values.push(value);
    }

    @Override
    public void execute(String operatorName) {
        Operator op = factory.operatorNamed(operatorName);
        op.accept(values);
    }

    @Override
    public void start(Calculator calculator) {
        calculator.toProgrammingMode();
    }

    @Override
    public void save(Calculator calculator, String name) {
        throw new IllegalStateException("Save can only be called after start");
    }
}
