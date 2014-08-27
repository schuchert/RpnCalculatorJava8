package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;

import java.math.BigDecimal;

public class If extends CompositeOperator {
    Else elseBlock;

    public If() {
        elseBlock = new Else();
    }

    @Override
    public void accept(RpnStack bigDecimals) {
        boolean condition = bigDecimals.pop().compareTo(BigDecimal.ZERO) != 0;
        if (condition) {
            super.accept(bigDecimals);
        } else {
            elseBlock.accept(bigDecimals);
        }
    }

    public void add(Operator op) {
        if (op instanceof Else) {
            elseBlock = (Else) op;
        } else {
            super.add(op);
        }
    }

    public int blockLevelChange() {
        return 1;
    }
}
