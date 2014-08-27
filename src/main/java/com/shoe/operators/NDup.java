package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class NDup implements OperatorRegistrar {
    public String name() {
        return "nDup";
    }

    @Override
    public Operator get() {
        return values -> {
            int count = values.pop().intValue();
            BigDecimal[] bigDecimals = values.top(count);
            IntStream.iterate(0, i -> ++i).limit(count).forEach(i -> values.push(bigDecimals));
        };
    }
}
