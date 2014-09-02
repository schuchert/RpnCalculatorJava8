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

    public RpnStack stackOf(int... values) {
        return Arrays
                .stream(values)
                .mapToObj(BigDecimal::valueOf)
                .collect(toCollection(RpnStack::new));
    }

    public void assertIsRegistered(String operatorName) {
        factory.operatorNamed(operatorName);
    }

    @Test
    public void assertIsRegistered() {
        assertIsRegistered(expectedName());
    }

    public String expectedName() {
        String baseName = getClass().getSimpleName().replaceAll("Test$", "");
        return Introspector.decapitalize(baseName);

    }
}
