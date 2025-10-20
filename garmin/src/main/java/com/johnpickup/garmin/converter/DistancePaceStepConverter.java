package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.workout.DistancePaceWorkoutStep;
import com.johnpickup.garmin.fit.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.parser.DistancePaceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class DistancePaceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistancePaceStep distancePaceStep = (DistancePaceStep)step;

        Distance d = new Distance(
                distancePaceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distancePaceStep.getDistance().getUnit()));
        PaceTarget p = PaceConverterFactory.getInstance().getPaceConverter(distancePaceStep.getPace()).convert(distancePaceStep.getPace());
        return new DistancePaceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d, p);
    }
}
