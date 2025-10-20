package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.TimeWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimeStep;

/**
 * Convert independent distance steps into Garmin Workout Distance Steps
 */
public class TimeStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimeStep timeStep = (TimeStep)step;

        Time t = new Time(timeStep.getTime().asDouble() * 60);
        return new TimeWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), t);
    }
}
