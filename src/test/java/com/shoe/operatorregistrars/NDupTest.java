package com.shoe.operatorregistrars;

import com.shoe.calculator.RpnStack;
import com.shoe.com.shoe.operatorregistrars.NDup;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Test;

import static com.shoe.calculator.TestUtilities.isBigDecimal;
import static org.junit.Assert.assertThat;

public class NDupTest extends BaseOperatorTest {
    @Override
    public String expectedName() {
        return "nDup";
    }

    @Test
    public void shouldDuplicateTop2ItemsOnStack() {
        RpnStack values = stackOf(1, 2, 2);
        new NDup().get().accept(values);
        assertThat(values.pop(), isBigDecimal(2));
        assertThat(values.pop(), isBigDecimal(1));
        assertThat(values.pop(), isBigDecimal(2));
        assertThat(values.pop(), isBigDecimal(1));
    }
}
