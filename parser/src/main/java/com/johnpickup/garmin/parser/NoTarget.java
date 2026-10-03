package com.johnpickup.garmin.parser;

/**
 * Singleton representing a step with no target.
 */
public class NoTarget implements Target {
    public static final NoTarget INSTANCE = new NoTarget();

    private NoTarget() {}

    @Override
    public String toString() {
        return "";
    }
}
