package com.shoe.calculator;

import java.math.BigDecimal;

public interface CalculatorState {
    public void enter(BigDecimal value);

    public void execute(String operatorName);

    public void start(Calculator calculator);

    public void save(Calculator calculator, String name);
}
