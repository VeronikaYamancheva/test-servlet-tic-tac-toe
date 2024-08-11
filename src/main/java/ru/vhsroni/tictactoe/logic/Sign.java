package ru.vhsroni.tictactoe.logic;

public enum Sign {
    EMPTY(' '),
    CROSS('X'),
    ZERO('0');

    private final char sign;

    Sign(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }
}