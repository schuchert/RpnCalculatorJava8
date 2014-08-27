package com.shoe.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RpnStackTest {
    @Test
    public void shouldNotThrowExceptionWhenPopingFromEptyStack() {
        assertThat(new RpnStack().pop(), is(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnZeroForEmptyStack() {
        assertThat(new RpnStack().peek(), is(BigDecimal.ZERO));
    }
}
