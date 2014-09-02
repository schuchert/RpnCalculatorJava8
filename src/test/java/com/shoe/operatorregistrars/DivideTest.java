package com.shoe.operatorregistrars;

import com.shoe.calculator.RpnStack;
import com.shoe.com.shoe.operatorregistrars.Divide;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.isBigDecimal;
import static org.junit.Assert.assertThat;

public class DivideTest extends BaseOperatorTest {
    @Test
    public void shouldDivideTwoNumbers() {
        RpnStack values = stackOf(10, 4);
        new Divide().get().accept(values);
        assertThat(values.peek(), isBigDecimal(2.5));
    }
}
