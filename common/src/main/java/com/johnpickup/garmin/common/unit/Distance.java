package com.johnpickup.garmin.common.unit;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Distance {
    private final double value;
    private final DistanceUnit unit;

    @Override
    public String toString() {
        if(value == (long) value)
            return String.format("%d%s",(long)value, unit.getShortName());
        else
            return String.format("%s%s", value, unit.getShortName());
    }

    public Float toGarminDistance() {
        return switch (unit) {
            case METRE -> (float) (value);
            case KILOMETRE -> (float) (value * 1000F);
            case MILE -> (float) (value * 1609F);
        };
    }
}
