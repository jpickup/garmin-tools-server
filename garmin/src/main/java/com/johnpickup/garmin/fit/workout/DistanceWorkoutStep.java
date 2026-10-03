package com.johnpickup.garmin.fit.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.NoTarget;
import com.johnpickup.garmin.common.unit.Target;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * Workout step that lasts a specific distance, carrying any target type.
 */
@EqualsAndHashCode(callSuper = true)
public class DistanceWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final Target target;

    public DistanceWorkoutStep(Intensity intensity, Distance distance) {
        super(intensity);
        this.distance = distance;
        this.target = NoTarget.INSTANCE;
    }

    public DistanceWorkoutStep(Intensity intensity, Distance distance, Target target) {
        super(intensity);
        this.distance = distance;
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    @Override
    public String getName() {
        return distance.toString() + (target instanceof NoTarget ? "" : " " + target);
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTargetMapper.toWktStepTarget(target.getTargetType()));
        step.setTargetValue(target.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(target.getGarminLow());
        step.setCustomTargetValueHigh(target.getGarminHigh());
        step.setNotes(nameWithIntensity());
        return Collections.singletonList(step);
    }
}
