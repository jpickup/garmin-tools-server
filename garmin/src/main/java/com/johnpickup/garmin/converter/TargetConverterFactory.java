package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.parser.Cadence;
import com.johnpickup.garmin.parser.HeartRate;
import com.johnpickup.garmin.parser.NoTarget;
import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.Power;

/**
 * Central dispatcher that converts any parser-side Target to a common.unit.Target
 * by routing to the appropriate per-discipline factory.
 */
public class TargetConverterFactory {

    private TargetConverterFactory() {}

    public static Target convert(com.johnpickup.garmin.parser.Target target) {
        return switch (target) {
            case null -> com.johnpickup.garmin.common.unit.NoTarget.INSTANCE;
            case NoTarget noTarget -> com.johnpickup.garmin.common.unit.NoTarget.INSTANCE;
            case Pace pace -> PaceConverterFactory.getInstance().getPaceConverter(pace).convert(pace);
            case HeartRate heartRate ->
                    HeartRateConverterFactory.getInstance().getHeartRateConverter(heartRate).convert(heartRate);
            case Power power -> PowerConverterFactory.getInstance().getPowerConverter(power).convert(power);
            case Cadence cadence -> CadenceConverterFactory.getInstance().getCadenceConverter(cadence).convert(cadence);
            default -> throw new IllegalArgumentException("Unknown target type: " + target.getClass().getName());
        };
    }
}
