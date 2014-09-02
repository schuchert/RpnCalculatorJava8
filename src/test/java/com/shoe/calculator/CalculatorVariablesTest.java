package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.assertResultWas;

public class CalculatorVariablesTest {
    Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
        calculator.enter(5);
        calculator.save("i");
    }

    @Test
    public void variableShouldBeSet() {
        calculator.execute("i");
        assertResultWas(calculator, 5);
        assertResultWas(calculator, 0);
    }

    @Test
    public void shouldBeAbleToChangeVariable() {
        calculator.enter(13);
        calculator.save("i");
        calculator.execute("i");
        assertResultWas(calculator, 13);
    }

}
