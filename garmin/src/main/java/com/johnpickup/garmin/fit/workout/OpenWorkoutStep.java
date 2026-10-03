package com.johnpickup.garmin.fit.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.NoTarget;
import com.johnpickup.garmin.common.unit.Target;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * Workout step with no fixed duration (ends on lap button press), carrying any target type.
 */
@EqualsAndHashCode(callSuper = true)
public class OpenWorkoutStep extends WorkoutStep {
    private final Target target;

    public OpenWorkoutStep(Intensity intensity) {
        super(intensity);
        this.target = NoTarget.INSTANCE;
    }

    public OpenWorkoutStep(Intensity intensity, Target target) {
        super(intensity);
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    @Override
    public String getName() {
        return "Open" + (target instanceof NoTarget ? "" : " " + target);
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.OPEN);
        step.setTargetType(WktStepTargetMapper.toWktStepTarget(target.getTargetType()));
        step.setTargetValue(target.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(target.getGarminLow());
        step.setCustomTargetValueHigh(target.getGarminHigh());
        step.setNotes(nameWithIntensity());
        return Collections.singletonList(step);
    }
}
