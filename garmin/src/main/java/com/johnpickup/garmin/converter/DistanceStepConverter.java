package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.DistanceWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.parser.DistanceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent distance steps into Garmin Workout Distance Steps
 */
public class DistanceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceStep distanceStep = (DistanceStep)step;

        Distance d = new Distance(
                distanceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceStep.getDistance().getUnit()));
        return new DistanceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d);
    }
}
