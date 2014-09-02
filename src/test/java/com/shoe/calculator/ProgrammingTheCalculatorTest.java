package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProgrammingTheCalculatorTest {
    private Calculator calculator;
    private String program;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    static org.hamcrest.Matcher<BigDecimal> isBigDecimal(long value) {
        return is(BigDecimal.valueOf(value));
    }

    void enter(int... values) {
        Arrays.stream(values).forEach(calculator::enter);
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

    void assertResultWas(int value) {
        assertThat(calculator.view(), isBigDecimal(value));
    }

    @Test
    public void shouldBeAbleToProgramCalculator() {
        createProgram("2 multiply").named("2x");

        calculator.enter(4);
        calculator.execute("2x");

        assertResultWas(8);
    }

    @Test
    public void shouldExecuteOperatorsInOrderProvided() {
        createProgram("add multiply subtract").named("ams");

        enter(3, 5, 2, 13);
        calculator.execute("ams");

        assertResultWas(-72);
    }

    @Test
    public void shouldBeAbleToProgramAnIfStatement() {
        createProgram("if drop end").named("dropIfTrue");

        enter(1, 2, 1);
        calculator.execute("dropIfTrue");

        assertResultWas(1);
    }

    @Test
    public void shouldBeAbleToProgramIfThenElse() {
        createProgram("if drop else swap end").named("ifThenElse");

        enter(13, 1, 2, 1);
        calculator.execute("ifThenElse");
        assertResultWas(1);

        enter(13, 2, 0);
        calculator.execute("ifThenElse");
        assertResultWas(13);
    }

    @Test
    public void shouldBeAbleToRepeatABlock() {
        createProgram("repeat drop end").named("dropSome");

        enter(4, 3, 2, 1, 2);
        calculator.execute("dropSome");

        assertResultWas(3);
    }

    @Test
    public void shouldBeAbleToProgramMinFunction() {
        createProgram("2 nDup < if drop else swap drop end").named("min");

        enter(6, 4);
        calculator.execute("min");

        assertResultWas(4);
    }

    @Test
    public void shouldBeAbleToSetVariables() {
        calculator.enter(5);
        calculator.save("i");
        calculator.execute("i");
        assertResultWas(5);
        calculator.execute("drop");
        assertResultWas(0);
    }

    @Test
    public void shouldBeAbleToChangeVariables() {
        calculator.enter(5);
        calculator.save("i");
        calculator.enter(13);
        calculator.save("i");
        calculator.execute("i");
        assertResultWas(13);
    }

    @Test
    public void canRepeatBasedOnVariable() {
        calculator.enter(3);
        calculator.save("i");

        createProgram("i repeat drop end").named("repeatI");

        enter(5, 4, 3, 2, 1);
        calculator.execute("repeatI");
        assertResultWas(4);
    }
}
