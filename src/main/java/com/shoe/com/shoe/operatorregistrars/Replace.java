package com.shoe.com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;
import com.shoe.calculator.RpnStack;

import java.math.BigDecimal;

public class Replace implements OperatorRegistrar {
    public static final BigDecimal MAX_INDEX = new BigDecimal(Integer.MAX_VALUE);

    @Override
    public Operator get() {
        return values -> {
            validateMinimumParametersPresent(values);

            BigDecimal newValue = values.pop();
            BigDecimal index = values.pop();

            validateReplacementIndex(values, index);

            BigDecimal[] originals = values.top(index.intValue());
            values.pop();
            values.push(newValue);
            values.push(originals);
        };
    }

    private void validateReplacementIndex(RpnStack values, BigDecimal index) {
        if (index.compareTo(new BigDecimal(values.size())) >= 0) {
            throw new IllegalArgumentException(String.format("Replace index of %s must be less than stack size of %s", index, values.size()));
        }

        if (index.compareTo(MAX_INDEX) >= 0) {
            throw new IllegalArgumentException(String.format("Replace index exceeds largest value of %s", MAX_INDEX));
        }
    }

    private void validateMinimumParametersPresent(RpnStack values) {
        if (values.size() < 3) {
            throw new IllegalArgumentException("Stack must have at least 3 elements");
        }
    }
}
