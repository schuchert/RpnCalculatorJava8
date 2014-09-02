package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static com.shoe.calculator.TestUtilities.*;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void lastNumberEnteredShouldBeWhatWeView() {
        calculator.enter(5);
        assertResultWas(calculator, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForUnknownOperator() {
        calculator.execute("@@@shouldnotbefound%%%");
    }

    @Test
    public void shouldBeAbleToGetContentsOfStack() {
        enter(calculator, 3, 4);
        Iterator<BigDecimal> values = calculator.display().iterator();
        assertThat(values.next(), isBigDecimal(3));
        assertThat(values.next(), isBigDecimal(4));
    }
}
