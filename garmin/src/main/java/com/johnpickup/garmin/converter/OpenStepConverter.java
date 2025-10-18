package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.OpenWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.parser.Step;

/**
 * Convert independent distance steps into Garmin Workout Distance Steps
 */
public class OpenStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenWorkoutStep openWorkoutStep = new OpenWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()));
        return openWorkoutStep;
    }
}
