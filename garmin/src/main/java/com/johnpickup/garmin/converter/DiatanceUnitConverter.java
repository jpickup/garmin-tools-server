package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.parser.DistanceUnit;

/**
 * Convert independent distance units into the Garmin type
 */
public class DiatanceUnitConverter {
    public static com.johnpickup.garmin.common.unit.DistanceUnit convert(DistanceUnit unit) {
        return switch (unit) {
            case KILOMETRE -> com.johnpickup.garmin.common.unit.DistanceUnit.KILOMETRE;
            case METRE -> com.johnpickup.garmin.common.unit.DistanceUnit.METRE;
            case MILE -> com.johnpickup.garmin.common.unit.DistanceUnit.MILE;
        };
    }
}
