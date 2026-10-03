package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.fit.workout.DistanceWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.parser.DistanceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Converts a parser DistanceStep (with any target) into a Garmin DistanceWorkoutStep.
 */
public class DistanceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceStep distanceStep = (DistanceStep) step;

        Distance d = new Distance(
                distanceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceStep.getDistance().getUnit()));

        Target target = TargetConverterFactory.convert(distanceStep.getTarget());

        return new DistanceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d, target);
    }
}
