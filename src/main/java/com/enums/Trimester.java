package com.enums;

import java.util.Random;

public enum Trimester {
    FIRST,
    SECOND,
    THIRD;

    private static final Trimester[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Trimester getRandomTrimester() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
