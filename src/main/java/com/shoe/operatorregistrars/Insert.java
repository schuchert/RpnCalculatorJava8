package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;
import com.shoe.calculator.RpnStack;

import java.math.BigDecimal;

public class Insert implements OperatorRegistrar {
    @Override
    public Operator get() {
        return values -> {
            checkMinimumStackSize(values);

            BigDecimal newValue = values.pop();
            int back = values.pop().intValue();

            checkValidIndex(values, back);

            BigDecimal[] originals = values.top(back);

            values.push(newValue);

            values.push(originals);
        };
    }

    private void checkValidIndex(RpnStack values, int back) {
        if (back > values.size()) {
            throw new IllegalArgumentException(String.format("insert at %d invalid, stack is too small, stack size is %d", back, values.size()));
        }
    }

    private void checkMinimumStackSize(RpnStack values) {
        if (values.size() < 3) {
            throw new IllegalArgumentException(String.format("insert requires at least 3 items on stack, but number of items is %d", values.size()));
        }
    }
}
