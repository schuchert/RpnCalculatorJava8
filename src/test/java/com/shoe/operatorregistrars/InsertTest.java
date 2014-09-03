package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static com.shoe.calculator.TestUtilities.isBigDecimal;
import static org.junit.Assert.assertThat;

public class InsertTest extends BaseOperatorTest {
    private Operator operator;

    @Before
    public void init() {
        operator = new Insert().get();
    }

    @Override
    public String expectedName() {
        return "insert";
    }

    @Test
    public void shouldBeAbleToInsertInAnotherLocation() {
        RpnStack stack = stackOf(6, 4, 19, 6, 8, 3, 311);
        operator.accept(stack);
        Iterator<BigDecimal> values = stack.iterator();
        assertThat(values.next(), isBigDecimal(6));
        assertThat(values.next(), isBigDecimal(4));
        assertThat(values.next(), isBigDecimal(311));
        assertThat(values.next(), isBigDecimal(19));
        assertThat(values.next(), isBigDecimal(6));
        assertThat(values.next(), isBigDecimal(8));
    }

    @Test
    public void shouldBeAbleToInsertBeforeSingleElement() {
        RpnStack stack = stackOf(41, 1, 19);
        operator.accept(stack);
        Iterator<BigDecimal> values = stack.iterator();
        assertThat(values.next(), isBigDecimal(19));
        assertThat(values.next(), isBigDecimal(41));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInsertionWhenIndexOutOfBounds() {
        RpnStack stack = stackOf(3, 19);
        operator.accept(stack);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInsertionWhenStackIsSmallerThan3() {
        RpnStack stack = stackOf();
        operator.accept(stack);
    }
}
