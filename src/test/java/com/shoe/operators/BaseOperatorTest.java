package com.shoe.operators;

import com.shoe.calculator.OperatorFactory;
import com.shoe.calculator.RpnStack;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.beans.Introspector;
import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.stream.Collectors.toCollection;
import static org.hamcrest.CoreMatchers.is;

public abstract class BaseOperatorTest {
    public static OperatorFactory factory = new OperatorFactory();

    public Matcher<BigDecimal> isBigDecimal(double value) {
        return is(BigDecimal.valueOf(value));
    }

    public Matcher<BigDecimal> isBigDecimal(long value) {
        return is(BigDecimal.valueOf(value));
    }

    public RpnStack stackOf(int... values) {
        return Arrays
                .stream(values)
                .mapToObj(BigDecimal::valueOf)
                .collect(toCollection(RpnStack::new));
//        return Arrays.stream(values)
//                .collect(
//                        RpnStack::new,
//                        (result, value) -> result.push(BigDecimal.valueOf(value)),
//                        (s1, s2) -> { }
//                );
    }

    public void assertIsRegistered(String operatorName) {
        factory.operatorNamed(operatorName);
    }

    @Test
    public void assertIsRegistered() {
        assertIsRegistered(expectedName());
    }

    public abstract String expectedName();
}
