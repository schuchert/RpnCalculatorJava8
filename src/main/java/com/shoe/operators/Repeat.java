package com.shoe.operators;

import com.shoe.calculator.RpnStack;

public class Repeat extends CompositeOperator {
    @Override
    public void accept(RpnStack bigDecimals) {
        int count = bigDecimals.pop().intValue();

        for (int i = 0; i < count; ++i) {
            super.accept(bigDecimals);
        }
    }

    public int blockLevelChange() {
        return 1;
    }
}
