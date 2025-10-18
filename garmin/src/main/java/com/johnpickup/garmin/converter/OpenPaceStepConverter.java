package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.OpenPaceWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.parser.OpenPaceStep;
import com.johnpickup.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class OpenPaceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenPaceStep distancePaceStep = (OpenPaceStep)step;

        PaceTarget p = PaceConverterFactory.getInstance().getPaceConverter(distancePaceStep.getPace()).convert(distancePaceStep.getPace());
        OpenPaceWorkoutStep openPaceWorkoutStep =
                new OpenPaceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), p);
        return openPaceWorkoutStep;
    }
}
