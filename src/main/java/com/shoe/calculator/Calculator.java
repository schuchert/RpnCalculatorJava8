package com.shoe.calculator;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class Calculator {
    CalculatorState state;
    private OperatorFactory factory;
    private RpnStack values;

    public Calculator() {
        factory = new OperatorFactory();
        values = new RpnStack();
        toExecutionMode();
    }

    public void enter(long value) {
        enter(BigDecimal.valueOf(value));
    }

    public void enter(BigDecimal value) {
        state.enter(value);
    }

    public BigDecimal view() {
        return values.peek();
    }

    public void execute(String opName) {
        state.execute(opName);
    }

    public Stream<BigDecimal> display() {
        return values.stream();
    }

    public void start() {
        state.start(this);
    }

    public void save(String operatorName) {
        state.save(this, operatorName);
        toExecutionMode();
    }

    void toExecutionMode() {
        state = new Executing(values, factory);
    }

    void toProgrammingMode() {
        state = new Programming(factory);
    }
}
