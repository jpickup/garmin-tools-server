package com.johnpickup.garmin.parser;

/**
 * Created by john on 03/01/2017.
 */
public enum PaceUnit {
    MIN_PER_MILE,
    MIN_PER_KILOMETRE,
    MILE_PER_HOUR,
    KILOMETRE_PER_HOUR;

    public static PaceUnit perDistanceUnit(DistanceUnit distanceUnit) {
        return switch (distanceUnit) {
            case KILOMETRE -> MIN_PER_KILOMETRE;
            case MILE -> MIN_PER_MILE;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case KILOMETRE_PER_HOUR -> "kph";
            case MILE_PER_HOUR -> "mph";
            case MIN_PER_KILOMETRE -> "/km";
            case MIN_PER_MILE -> "/mi";
        };
    }
}
