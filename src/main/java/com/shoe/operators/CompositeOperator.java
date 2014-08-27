package com.shoe.operators;

import com.shoe.calculator.Operator;
import com.shoe.calculator.RpnStack;

import java.util.LinkedList;
import java.util.List;

public class CompositeOperator implements Operator {
    List<Operator> operators = new LinkedList<Operator>();

    @Override
    public void accept(RpnStack bigDecimals) {
        operators.stream().forEach(op -> op.accept(bigDecimals));
    }

    public void add(Operator child) {
        operators.add(child);
    }

    public int blockLevelChange() {
        return 0;
    }
}
