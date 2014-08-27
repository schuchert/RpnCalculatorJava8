package com.shoe.operators;

import com.shoe.calculator.RpnStack;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class DivideTest extends BaseOperatorTest {
    @Override
    public String expectedName() {
        return "/";
    }

    @Test
    public void shouldDivideTwoNumbers() {
        RpnStack values = stackOf(10, 4);
        new Divide().get().accept(values);
        assertThat(values.peek(), isBigDecimal(2.5));
    }
}
