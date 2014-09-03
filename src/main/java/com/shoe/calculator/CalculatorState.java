package com.shoe.calculator;

import java.math.BigDecimal;

public interface CalculatorState {
    public void enter(BigDecimal value);

    public void execute(String operatorName);

    public void start(CalculationContext context);

    public void save(CalculationContext context, String name);
}
