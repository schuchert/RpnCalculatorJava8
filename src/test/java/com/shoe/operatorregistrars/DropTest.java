package com.shoe.operatorregistrars;

import com.shoe.calculator.RpnStack;
import com.shoe.com.shoe.operatorregistrars.Drop;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.isBigDecimal;
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
