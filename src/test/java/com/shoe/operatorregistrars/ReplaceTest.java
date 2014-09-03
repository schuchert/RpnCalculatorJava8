package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.shoe.calculator.TestUtilities.isBigDecimal;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReplaceTest extends BaseOperatorTest {
    private Operator operator;

    @Before
    public void init() {
        operator = new Replace().get();
    }

    @Override
    public String expectedName() {
        return "replace";
    }

    @Test
    public void shouldBeRegistered() {
        assertIsRegistered("replace");
    }

    @Test
    public void shouldBeAbleToReplaceTopOfStack() {
        RpnStack values = stackOf(13, 0, 11);
        operator.accept(values);
        assertThat(values.size(), is(1));
        assertThat(values.peek(), isBigDecimal(11));
    }

    @Test
    public void shouldBeAbleToReplaceMiddleOfStack() {
        RpnStack values = stackOf(10, 20, 30, 40, 2, 13);
        operator.accept(values);
        assertThat(values.size(), is(4));
        values.pop();
        values.pop();
        assertThat(values.peek(), isBigDecimal(13));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfStackTooShort() {
        RpnStack values = stackOf(1, 3);
        operator.accept(values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfIndexOutOfBounds() {
        RpnStack values = stackOf(11, 1, 4);
        operator.accept(values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfIndexTooLarge() {
        RpnStack values = stackOf(11);
        values.push(new BigDecimal(Integer.MAX_VALUE));
        values.push(BigDecimal.ONE);
        operator.accept(values);
    }
}
