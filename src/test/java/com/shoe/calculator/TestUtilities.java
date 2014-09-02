package com.shoe.calculator;

import org.hamcrest.Matcher;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestUtilities {
    public static Matcher<BigDecimal> isBigDecimal(double value) {
        return is(BigDecimal.valueOf(value));
    }

    public static Matcher<BigDecimal> isBigDecimal(long value) {
        return is(BigDecimal.valueOf(value));
    }

    public static void assertResultWas(Calculator calculator, int value) {
        assertThat(calculator.view(), isBigDecimal(value));
        calculator.execute("drop");
    }

    static void enter(Calculator calculator, int... values) {
        Arrays.stream(values).forEach(calculator::enter);
    }

}
