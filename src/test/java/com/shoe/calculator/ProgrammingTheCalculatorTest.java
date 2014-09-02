package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProgrammingTheCalculatorTest {
    private Calculator calculator;

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
        createProgramNamed("2 multiply", "2x");

        calculator.enter(4);
        calculator.execute("2x");
        assertThat(calculator.view(), isBigDecimal(8));
    }

    @Test
    public void shouldExecuteOperatorsInOrderProvided() {
        createProgramNamed("add multiply subtract", "ams");

        enter(3, 5, 2, 13);
        calculator.execute("ams");
        assertThat(calculator.view(), isBigDecimal(-72));
    }

    @Test
    public void shouldBeAbleToProgramAnIfStatement() {
        createProgramNamed("if drop end", "dropIfTrue");
        enter(1, 2, 1);
        calculator.execute("dropIfTrue");
        assertThat(calculator.view(), isBigDecimal(1));
    }

    @Test
    public void shouldBeAbleToProgramIfThenElse() {
        createProgramNamed("if drop else swap end", "ifThenElse");
        enter(13, 1, 2, 1);
        calculator.execute("ifThenElse");
        assertThat(calculator.view(), isBigDecimal(1));
        enter(13, 2, 0);
        calculator.execute("ifThenElse");
        assertThat(calculator.view(), isBigDecimal(13));
    }

    @Test
    public void shouldBeAbleToRepeatABlock() {
        createProgramNamed("repeat drop end", "dropSome");
        enter(4, 3, 2, 1, 2);
        calculator.execute("dropSome");
        assertThat(calculator.view(), isBigDecimal(3));
    }

    @Test
    public void shouldBeAbleToProgramMinFunction() {
        createProgramNamed("2 nDup < if drop else swap drop end", "min");
        enter(6, 4);
        calculator.execute("min");
        assertThat(calculator.view(), isBigDecimal(4));
    }

    @Test
    public void shouldBeAbleToSetVariables() {
        calculator.enter(5);
        calculator.save("i");
        calculator.execute("i");
        assertThat(calculator.view(), isBigDecimal(5));
        calculator.execute("drop");
        assertThat(calculator.view(), isBigDecimal(0));
    }

    @Test
    public void shouldBeAbleToChangeVariables() {
        calculator.enter(5);
        calculator.save("i");
        calculator.enter(13);
        calculator.save("i");
        calculator.execute("i");
        assertThat(calculator.view(), isBigDecimal(13));
    }

    @Test
    public void canRepeatBasedOnVariable() {
        calculator.enter(3);
        calculator.save("i");
        createProgramNamed("i repeat drop end", "repeatI");

        enter(5, 4, 3, 2, 1);
        calculator.execute("repeatI");
        assertThat(calculator.view(), isBigDecimal(4));
    }
}
