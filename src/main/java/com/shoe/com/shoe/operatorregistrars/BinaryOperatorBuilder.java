package com.shoe.com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class BinaryOperatorBuilder {
    public static Operator build(BiFunction<BigDecimal, BigDecimal, BigDecimal> f) {
        return values -> {
            BigDecimal rhs = values.pop();
            BigDecimal lhs = values.pop();
            values.push(f.apply(lhs, rhs));
        };
    }

}
