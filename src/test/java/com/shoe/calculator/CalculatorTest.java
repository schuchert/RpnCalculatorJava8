package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    static org.hamcrest.Matcher<BigDecimal> isBigDecimal(long value) {
        return is(BigDecimal.valueOf(value));
    }

    @Test
    public void lastNumberEnteredShouldBeWhatWeView() {
        calculator.enter(5);
        assertThat(calculator.view(), isBigDecimal(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForUnknownOperator() {
        calculator.execute("@@@shouldnotbefound%%%");
    }

    @Test
    public void shouldBeAbleToGetContentsOfStack() {
        calculator.enter(3);
        calculator.enter(4);
        Iterator<BigDecimal> values = calculator.display().iterator();
        assertThat(values.next(), isBigDecimal(3));
        assertThat(values.next(), isBigDecimal(4));
    }
}
