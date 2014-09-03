package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.assertResultWas;

public class SizeTest extends BaseOperatorTest {
    @Test
    public void shouldPutStackSizeOnStack() {
        RpnStack stack = stackOf(1, 2, 3, 4);
        Operator operator = new Size().get();
        operator.accept(stack);
        assertResultWas(stack, 4);
    }
}
