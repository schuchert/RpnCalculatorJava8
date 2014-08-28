package com.shoe.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    static org.hamcrest.Matcher<BigDecimal> isBigDecimal(long value) {
        return is(BigDecimal.valueOf(value));
    }

    @Test
    public void lastNumberEnteredShouldBeWhatWeView() {
        calculator.enter(5);
        assertThat(calculator.view(), isBigDecimal(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForUnknownOperator() {
        calculator.execute("@@@shouldnotbefound%%%");
    }

    @Test
    public void shouldBeAbleToGetContentsOfStack() {
        calculator.enter(3);
        calculator.enter(4);
        Iterator<BigDecimal> values = calculator.display().iterator();
        assertThat(values.next(), isBigDecimal(3));
        assertThat(values.next(), isBigDecimal(4));
    }

    @Test
    public void shouldBeAbleToProgramCalculator() {
        calculator.start();
        calculator.enter(2);
        calculator.execute("*");
        calculator.save("2x");
        calculator.enter(4);
        calculator.execute("2x");
        assertThat(calculator.view(), isBigDecimal(8));
    }

    @Test
    public void shouldExecuteOperatorsInOrderProvided() {
        calculator.start();
        calculator.execute("+");
        calculator.execute("*");
        calculator.execute("-");
        calculator.save("ams");
        calculator.enter(3);
        calculator.enter(5);
        calculator.enter(2);
        calculator.enter(13);
        calculator.execute("ams");
        assertThat(calculator.view(), isBigDecimal(-72));
    }

    @Test
    public void shouldBeAbleToProgramAnIfStatement() {
        calculator.start();
        calculator.execute("if");
        calculator.execute("drop");
        calculator.execute("end");
        calculator.save("dropIfTrue");
        calculator.enter(1);
        calculator.enter(2);
        calculator.enter(1);
        calculator.execute("dropIfTrue");
        assertThat(calculator.view(), isBigDecimal(1));
    }

    @Test
    public void shouldBeAbleToProgramIfThenElse() {
        calculator.start();
        calculator.execute("if");
        calculator.execute("drop");
        calculator.execute("else");
        calculator.execute("swap");
        calculator.execute("end");
        calculator.save("ifThenElse");
        calculator.enter(13);
        calculator.enter(1);
        calculator.enter(2);
        calculator.enter(1);
        calculator.execute("ifThenElse");
        assertThat(calculator.view(), isBigDecimal(1));
        calculator.enter(13);
        calculator.enter(2);
        calculator.enter(0);
        calculator.execute("ifThenElse");
        assertThat(calculator.view(), isBigDecimal(13));

    }

    @Test
    public void shouldBeAbleToRepeatABlock() {
        calculator.start();
        calculator.execute("repeat");
        calculator.execute("drop");
        calculator.execute("end");
        calculator.save("dropSome");
        calculator.enter(4);
        calculator.enter(3);
        calculator.enter(2);
        calculator.enter(1);
        calculator.enter(2);
        calculator.execute("dropSome");
        assertThat(calculator.view(), isBigDecimal(3));
    }

    @Test
    public void shouldBeAbleToProgramMinFunction() {
        calculator.start();
        calculator.enter(2);
        calculator.execute("nDup");
        calculator.execute("<");
        calculator.execute("if");
        calculator.execute("drop");
        calculator.execute("else");
        calculator.execute("swap");
        calculator.execute("drop");
        calculator.execute("end");
        calculator.save("min");
        calculator.enter(6);
        calculator.enter(4);
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
        calculator.start();
        calculator.execute("i");
        calculator.execute("repeat");
        calculator.execute("drop");
        calculator.execute("end");
        calculator.save("repeatI");

        calculator.enter(5);
        calculator.enter(4);
        calculator.enter(3);
        calculator.enter(2);
        calculator.enter(1);
        calculator.execute("repeatI");
        assertThat(calculator.view(), isBigDecimal(4));
    }
}
