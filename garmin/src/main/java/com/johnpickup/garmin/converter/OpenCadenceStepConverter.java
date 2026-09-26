package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.fit.workout.OpenCadenceWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.parser.OpenCadenceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent cadence steps into the Garmin equivalent
 */
public class OpenCadenceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenCadenceStep openCadenceStep = (OpenCadenceStep)step;

        CadenceTarget cadenceTarget = CadenceConverterFactory.getInstance()
                .getCadenceConverter(openCadenceStep.getCadence())
                .convert(openCadenceStep.getCadence());

        return new OpenCadenceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), cadenceTarget);
    }
}
