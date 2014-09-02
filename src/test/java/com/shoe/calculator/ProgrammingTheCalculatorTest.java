package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.shoe.calculator.TestUtilities.assertResultWas;
import static com.shoe.calculator.TestUtilities.enter;

public class ProgrammingTheCalculatorTest {
    private Calculator calculator;
    private String program;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    ProgrammingTheCalculatorTest createProgram(String program) {
        this.program = program;
        return this;
    }

    void named(String name) {
        createProgramNamed(program, name);
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

    @Test
    public void shouldBeAbleToProgramCalculator() {
        createProgram("2 multiply").named("2x");

        calculator.enter(4);
        calculator.execute("2x");

        assertResultWas(calculator, 8);
    }

    @Test
    public void shouldExecuteOperatorsInOrderProvided() {
        createProgram("add multiply subtract").named("ams");

        enter(calculator, 3, 5, 2, 13);
        calculator.execute("ams");

        assertResultWas(calculator, -72);
    }

    @Test
    public void shouldBeAbleToProgramAnIfStatement() {
        createProgram("if drop end").named("dropIfTrue");

        enter(calculator, 1, 2, 1);
        calculator.execute("dropIfTrue");

        assertResultWas(calculator, 1);
    }

    @Test
    public void shouldBeAbleToProgramIfThenElse() {
        createProgram("if drop else swap end").named("ifThenElse");

        enter(calculator, 13, 1, 2, 1);
        calculator.execute("ifThenElse");
        assertResultWas(calculator, 1);

        enter(calculator, 13, 2, 0);
        calculator.execute("ifThenElse");
        assertResultWas(calculator, 13);
    }

    @Test
    public void shouldBeAbleToRepeatABlock() {
        createProgram("repeat drop end").named("dropSome");

        enter(calculator, 4, 3, 2, 1, 2);
        calculator.execute("dropSome");

        assertResultWas(calculator, 3);
    }

    @Test
    public void shouldBeAbleToProgramMinFunction() {
        createProgram("2 nDup < if drop else swap drop end").named("min");

        enter(calculator, 6, 4);
        calculator.execute("min");

        assertResultWas(calculator, 4);
    }

    @Test
    public void shouldBeAbleToSetVariables() {
        calculator.enter(5);
        calculator.save("i");
        calculator.execute("i");
        assertResultWas(calculator, 5);
        assertResultWas(calculator, 0);
    }

    @Test
    public void shouldBeAbleToChangeVariables() {
        calculator.enter(5);
        calculator.save("i");
        calculator.enter(13);
        calculator.save("i");
        calculator.execute("i");
        assertResultWas(calculator, 13);
    }

    @Test
    public void canRepeatBasedOnVariable() {
        calculator.enter(3);
        calculator.save("i");

        createProgram("i repeat drop end").named("repeatI");

        enter(calculator, 5, 4, 3, 2, 1);
        calculator.execute("repeatI");
        assertResultWas(calculator, 4);
    }
}
