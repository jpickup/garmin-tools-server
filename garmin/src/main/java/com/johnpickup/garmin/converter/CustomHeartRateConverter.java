package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.CustomHeartRateTarget;
import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.garmin.common.unit.HeartRateUnit;
import com.johnpickup.garmin.parser.HeartRate;
import com.johnpickup.garmin.parser.HeartRateRange;

public class CustomHeartRateConverter implements HeartRateConverter {
    @Override
    public HeartRateTarget convert(HeartRate heartRate) {
        HeartRateRange heartRateRange = (HeartRateRange) heartRate;
        HeartRateUnit unit = switch (heartRateRange.getUnit()) {
            case BPM -> HeartRateUnit.BEATS_PER_MINUTE;
        };

        return new CustomHeartRateTarget(heartRateRange.getMinimum(), heartRateRange. getMaximum(), unit);
    }
}
