package com.johnpickup.garmin.common.unit;

/**
 * Garmin-side target - implemented by all target types that can be attached to a workout step.
 * Provides the values needed to populate a FIT WorkoutStepMesg target.
 */
public interface Target {
    TargetType getTargetType();
    Long getGarminLow();
    Long getGarminHigh();
    Long getTargetValue();
}
