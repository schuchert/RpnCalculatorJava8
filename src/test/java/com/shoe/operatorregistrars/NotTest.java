package com.shoe.operatorregistrars;

import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.assertResultWas;

public class NotTest extends BaseOperatorTest {
    @Test
    public void shouldChangeTrueToFalse() {
        RpnStack stack = stackOf(12);
        new Not().get().accept(stack);
        assertResultWas(stack, 0);
    }

    @Test
    public void shouldChangeFalseToTrue() {
        RpnStack stack = stackOf(0);
        new Not().get().accept(stack);
        assertResultWas(stack, 1);
    }
}
