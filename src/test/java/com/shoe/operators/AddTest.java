package com.shoe.operators;

import com.shoe.calculator.RpnStack;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class AddTest extends BaseOperatorTest {
    @Override
    public String expectedName() {
        return "add";
    }

    @Test
    public void shouldAddTwoNumbers() {
        RpnStack values = stackOf(3, 4);
        new Add().get().accept(values);
        assertThat(values.peek(), isBigDecimal(7));
    }
}
