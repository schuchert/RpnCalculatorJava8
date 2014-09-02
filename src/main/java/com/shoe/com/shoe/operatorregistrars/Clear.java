package com.shoe.com.shoe.operatorregistrars;

import com.shoe.calculator.Operator;
import com.shoe.calculator.OperatorRegistrar;

import java.util.Vector;

public class Clear implements OperatorRegistrar {
    public Operator get() {
        return Vector::clear;
    }
}
