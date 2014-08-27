package com.shoe.operators;

import com.shoe.calculator.RpnStack;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PushValueTest {

    public static final BigDecimal LIFE_THE_UNIVERSE_AND_EVERYTHING = BigDecimal.valueOf(42);

    @Test
    public void shouldPushValueOnStack() {
        RpnStack stack = new RpnStack();
        new PushValue(LIFE_THE_UNIVERSE_AND_EVERYTHING).accept(stack);
        assertThat(stack.peek(), is(LIFE_THE_UNIVERSE_AND_EVERYTHING));
    }
}
