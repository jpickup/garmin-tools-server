package com.johnpickup.garmin.common.unit;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Encapsulation of custom heart rate value with human-readable toString plus a conversion to Garmin units
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class HeartRate {
    private final long value;
    private final HeartRateUnit unit;

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public Long toGarminHeartRate() {
        // Garmin HR units are in bpm + 100 ( 0 .. 100 reserved for HR %)
        return switch (unit) {
            case BEATS_PER_MINUTE -> value + 100;
        };
    }

    public String toValueString() {
        return switch (unit) {
            case BEATS_PER_MINUTE -> String.format("%d", value);
        };
    }
}
