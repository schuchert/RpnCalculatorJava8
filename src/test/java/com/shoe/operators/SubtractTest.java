package com.shoe.operators;

import com.shoe.calculator.RpnStack;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class SubtractTest extends BaseOperatorTest {
    @Test
    public void shouldAddTwoNumbers() {
        RpnStack values = stackOf(3, 4);
        new Subtract().get().accept(values);
        assertThat(values.peek(), isBigDecimal(-1));
    }
}
