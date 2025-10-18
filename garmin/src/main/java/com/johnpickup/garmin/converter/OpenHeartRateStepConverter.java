package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.OpenHeartRateWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.parser.OpenHeartRateStep;
import com.johnpickup.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class OpenHeartRateStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenHeartRateStep openHeartRateStep = (OpenHeartRateStep)step;

        HeartRateTarget heartRateTarget = HeartRateConverterFactory.getInstance()
                .getHeartRateConverter(openHeartRateStep.getHeartRate())
                .convert(openHeartRateStep.getHeartRate());

        OpenHeartRateWorkoutStep openHeartRateWorkoutStep =
                new OpenHeartRateWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), heartRateTarget);
        return openHeartRateWorkoutStep;
    }
}
