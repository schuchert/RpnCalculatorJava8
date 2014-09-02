package com.shoe.calculator;

import java.math.BigDecimal;
import java.util.Arrays;

public class CalculatorProgrammer {
    private Calculator calculator;
    String program;

    CalculatorProgrammer(Calculator calculator, String program) {
        this.calculator = calculator;
        this.program = program;
    }

    public void named(String programName) {
        createProgramNamed(program, programName);
    }

    void createProgramNamed(String program, String name) {
        String[] parts = program.split(" ");

        calculator.start();

        Arrays.stream(parts).forEach((String p) -> {
            if (p.matches("\\d+")) {
                calculator.enter(new BigDecimal(p));
            } else {
                calculator.execute(p);
            }
        });

        calculator.save(name);
    }
}
