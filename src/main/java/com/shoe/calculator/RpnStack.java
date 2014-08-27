package com.shoe.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Stack;

public class RpnStack extends Stack<BigDecimal> {
    public BigDecimal pop() {
        if (size() > 0) {
            return super.pop();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal peek() {
        if (size() > 0) {
            return super.peek();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal[] top(int count) {
        BigDecimal[] original = new BigDecimal[count];
        for (int i = 0; i < count; ++i) {
            original[original.length - 1 - i] = pop();
        }
        return original;
    }

    public void push(BigDecimal[] values) {
        Arrays.stream(values).forEach(value -> push(value));
    }
}
