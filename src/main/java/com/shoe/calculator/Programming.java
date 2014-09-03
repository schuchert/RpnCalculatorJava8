package com.shoe.calculator;

import com.shoe.operators.*;

import java.math.BigDecimal;
import java.util.Stack;

public class Programming implements CalculatorState {
    private Stack<CompositeOperator> blocks;
    private OperatorFactory factory;

    public Programming(OperatorFactory factory) {
        this.factory = factory;
        blocks = new Stack<>();
        blocks.push(new CompositeOperator());
    }

    @Override
    public void enter(BigDecimal value) {
        blocks.peek().add(new PushValue(value));
    }

    @Override
    public void execute(String operatorName) {
        Operator op = factory.operatorNamed(operatorName);
        blocks.peek().add(op);
        if (op instanceof If || op instanceof Repeat) {
            blocks.push((CompositeOperator) op);
        } else if (op instanceof End) {
            blocks.pop();
        } else if (op instanceof Else) {
            blocks.pop();
            blocks.push((Else) op);
        }
    }

    @Override
    public void start(CalculationContext context) {
        throw new IllegalStateException("Start should not be called after start but before save");
    }

    @Override
    public void save(CalculationContext context, String operatorName) {
        factory.register(operatorName, blocks.pop());
        context.toExecutionMode();
    }
}
