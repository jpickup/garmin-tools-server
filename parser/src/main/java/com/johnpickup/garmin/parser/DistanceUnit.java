package com.johnpickup.garmin.parser;

/**
 * Created by john on 03/01/2017.
 */
public enum DistanceUnit {
    METRE,
    KILOMETRE,
    MILE;

    @Override
    public String toString() {
        return switch (this) {
            case METRE -> "m";
            case KILOMETRE -> "km";
            case MILE -> "mi";
        };
    }
}
