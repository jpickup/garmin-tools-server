package com.johnpickup.garmin.fit.workout;

import com.garmin.fit.WktStepTarget;
import com.johnpickup.garmin.common.unit.TargetType;

/**
 * Maps the FIT-SDK-free TargetType enum to the FIT-SDK WktStepTarget enum.
 * Keeps the common module free of FIT-SDK dependencies.
 */
final class WktStepTargetMapper {

    private WktStepTargetMapper() {}

    static WktStepTarget toWktStepTarget(TargetType targetType) {
        return switch (targetType) {
            case NONE -> WktStepTarget.OPEN;
            case PACE -> WktStepTarget.SPEED;
            case HEART_RATE -> WktStepTarget.HEART_RATE;
            case POWER -> WktStepTarget.POWER;
            case CADENCE -> WktStepTarget.CADENCE;
        };
    }
}
