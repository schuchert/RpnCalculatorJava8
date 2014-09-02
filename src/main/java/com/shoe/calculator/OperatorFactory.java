package com.shoe.calculator;

import com.shoe.operators.CompositeOperator;
import org.reflections.Reflections;

import java.beans.Introspector;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OperatorFactory {
    private Map<String, Operator> operators = new ConcurrentHashMap<>();
    private Map<String, Class<CompositeOperator>> compositeOperators = new ConcurrentHashMap<>();

    public OperatorFactory() {
        scanClasspath();
    }

    private void scanClasspath() {
        Reflections reflections = new Reflections("com.shoe.operators");
        handleOperatorRegistrars(reflections);
        handleCompositeOperators(reflections);
    }

    private void handleCompositeOperators(Reflections reflections) {
        reflections.getSubTypesOf(CompositeOperator.class).stream().forEach(clazz -> {
            try {
                compositeOperators.put(Introspector.decapitalize(clazz.getSimpleName()), (Class<CompositeOperator>) clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void handleOperatorRegistrars(Reflections reflections) {
        reflections.getSubTypesOf(OperatorRegistrar.class).stream().forEach(clazz -> {
            try {
                OperatorRegistrar op = clazz.newInstance();
                register(op.name(), op.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void register(String name, Operator op) {
        validateParameters(name, op);
        operators.put(name, op);
    }

    private void validateParameters(String name, Operator op) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Operator names must be at least 1 character long");
        }
        if (operators.get(name) != null) {
            if (operators.get(name).getClass() != op.getClass()) {
                throw new AlreadyRegisteredException(name);
            }
        }
        if (op == null) {
            throw new IllegalArgumentException("Operator cannot be null");
        }
    }

    public Operator operatorNamed(String s) {
        Operator operator = operators.get(s);
        if (operator == null) {
            operator = attemptToBuildCompositeOperator(s);
        }

        if (operator == null) {
            throw new IllegalArgumentException(String.format("Operator %s does not exist.", s));
        }

        return operator;
    }

    private Operator attemptToBuildCompositeOperator(String s) {
        Class<CompositeOperator> clazz = compositeOperators.get(s);
        Operator operator = null;

        if (clazz != null) {
            try {
                operator = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Unable to create instance of " + clazz, e);
            }
        }

        return operator;
    }
}
