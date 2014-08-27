package com.shoe.operators;

import com.shoe.calculator.RpnStack;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class MultiplyTest extends BaseOperatorTest {
    @Override
    public String expectedName() {
        return "*";
    }

    @Test
    public void shouldAddTwoNumbers() {
        RpnStack values = stackOf(3, 4);
        new Multiply().get().accept(values);
        assertThat(values.peek(), isBigDecimal(12));
    }
}
