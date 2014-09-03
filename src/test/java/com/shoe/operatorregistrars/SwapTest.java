package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Before;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.isBigDecimal;
import static org.junit.Assert.assertThat;

public class SwapTest extends BaseOperatorTest {
    private Operator operator;

    @Before
    public void init() {
        operator = new Swap().get();
    }

    @Override
    public String expectedName() {
        return "swap";
    }

    @Test
    public void verifyIsRegistered() {
        assertIsRegistered("swap");
    }

    @Test
    public void shouldSwapTopTwoElements() {
        RpnStack values = stackOf(1, 2);
        operator.accept(values);
        assertThat(values.pop(), isBigDecimal(1));
        assertThat(values.pop(), isBigDecimal(2));
    }
}
