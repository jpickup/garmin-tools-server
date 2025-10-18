package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.DistancePowerWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.parser.DistancePowerStep;
import com.johnpickup.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class DistancePowerStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistancePowerStep distancePowerStep = (DistancePowerStep)step;

        Distance d = new Distance(
                distancePowerStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distancePowerStep.getDistance().getUnit()));

        PowerTarget powerTarget = PowerConverterFactory.getInstance()
                .getPowerConverter(distancePowerStep.getPower())
                .convert(distancePowerStep.getPower());

        return new DistancePowerWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d, powerTarget);
    }
}
