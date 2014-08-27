package com.shoe.operators;

import com.shoe.calculator.RpnStack;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class DropTest extends BaseOperatorTest {
    @Override
    public String expectedName() {
        return "drop";
    }

    @Test
    public void shouldDropTopValue() {
        RpnStack values = stackOf(4, 2);
        new Drop().get().accept(values);
        assertThat(values.peek(), isBigDecimal(4));
    }
}
