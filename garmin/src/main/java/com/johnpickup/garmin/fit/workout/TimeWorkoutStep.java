package com.johnpickup.garmin.fit.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.NoTarget;
import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.common.unit.Time;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * Workout step that lasts a specific time, carrying any target type.
 */
@EqualsAndHashCode(callSuper = true)
public class TimeWorkoutStep extends WorkoutStep {
    private final Time time;
    private final Target target;

    public TimeWorkoutStep(Intensity intensity, Time time) {
        super(intensity);
        this.time = time;
        this.target = NoTarget.INSTANCE;
    }

    public TimeWorkoutStep(Intensity intensity, Time time, Target target) {
        super(intensity);
        this.time = time;
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    @Override
    public String getName() {
        return time.toString() + (target instanceof NoTarget ? "" : " " + target);
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
        step.setTargetType(WktStepTargetMapper.toWktStepTarget(target.getTargetType()));
        step.setTargetValue(target.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(target.getGarminLow());
        step.setCustomTargetValueHigh(target.getGarminHigh());
        step.setNotes(nameWithIntensity());
        return Collections.singletonList(step);
    }
}
