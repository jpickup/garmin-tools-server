package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.TimePowerWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.parser.Step;
import com.johnpickup.parser.TimePowerStep;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class TimePowerStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimePowerStep timePowerStep = (TimePowerStep)step;

        Time t = new Time(timePowerStep.getTime().asDouble() * 60);

        PowerTarget powerTarget = PowerConverterFactory.getInstance()
                .getPowerConverter(timePowerStep.getPower())
                .convert(timePowerStep.getPower());

        return new TimePowerWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), t, powerTarget);
    }
}
