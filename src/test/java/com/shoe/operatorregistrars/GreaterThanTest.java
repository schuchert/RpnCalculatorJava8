package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GreaterThanTest extends BaseOperatorTest {

    private Operator op;

    @Before
    public void init() {
        op = new GreaterThan().get();
    }

    @Override
    public String expectedName() {
        return "<";
    }

    @Test
    public void shouldBeZeroForLessThan() {
        RpnStack values = stackOf(1, 2);
        op.accept(values);
        assertThat(values.peek(), is(BigDecimal.ZERO));
    }

    @Test
    public void shouldBeZeroForEqual() {
        RpnStack values = stackOf(1, 1);
        op.accept(values);
        assertThat(values.peek(), is(BigDecimal.ZERO));
    }

    @Test
    public void shouldBeOneForGreaterThan() {
        RpnStack values = stackOf(2, 1);
        op.accept(values);
        assertThat(values.peek(), is(BigDecimal.ONE));
    }
}
