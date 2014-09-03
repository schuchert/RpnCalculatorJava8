package com.shoe.operatorregistrars;

import com.shoe.calculator.RpnStack;
import com.shoe.operators.BaseOperatorTest;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClearTest extends BaseOperatorTest {
    @Override
    public String expectedName() {
        return "clear";
    }

    @Test
    public void shouldClearStack() {
        RpnStack values = stackOf(3, 4);
        new Clear().get().accept(values);
        assertThat(values.size(), is(0));
    }
}
