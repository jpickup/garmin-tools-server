package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.DistanceHeartRateWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.garmin.parser.DistanceHeartRateStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class DistanceHeartRateStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceHeartRateStep distanceHeartRateStep = (DistanceHeartRateStep)step;

        Distance d = new Distance(
                distanceHeartRateStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceHeartRateStep.getDistance().getUnit()));

        HeartRateTarget heartRateTarget = HeartRateConverterFactory.getInstance()
                .getHeartRateConverter(distanceHeartRateStep.getHeartRate())
                .convert(distanceHeartRateStep.getHeartRate());

        return new DistanceHeartRateWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d, heartRateTarget);
    }
}
