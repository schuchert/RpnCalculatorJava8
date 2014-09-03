package com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;
import com.shoe.calculator.RpnStack;

public class Drop implements OperatorRegistrar {
    @Override
    public Operator get() {
        return RpnStack::pop;
    }
}
