package com.shoe.calculator;

public class AlreadyRegisteredException extends RuntimeException {
    public final String name;

    public AlreadyRegisteredException(String name) {
        this.name = name;
    }
}
