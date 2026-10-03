package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.fit.workout.OpenWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.parser.OpenStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Converts a parser OpenStep (with any target) into a Garmin OpenWorkoutStep.
 */
public class OpenStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenStep openStep = (OpenStep) step;

        Target target = TargetConverterFactory.convert(openStep.getTarget());

        return new OpenWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), target);
    }
}
