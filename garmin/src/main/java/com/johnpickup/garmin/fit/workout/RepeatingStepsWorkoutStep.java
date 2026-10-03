package com.johnpickup.garmin.fit.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Workout that repeats the component steps a specific number of times
 */
@EqualsAndHashCode(callSuper = true)
public class RepeatingStepsWorkoutStep extends WorkoutStep {
    private final int intervalCount;
    private final List<WorkoutStep> steps;

    public RepeatingStepsWorkoutStep(Intensity intensity, int intervalCount, List<WorkoutStep> steps) {
        super(intensity);
        this.intervalCount = intervalCount;
        this.steps = steps;
    }

    @Override
    public String getName() {
        StringBuilder result = new StringBuilder(String.format("%d x (", intervalCount));
        for (WorkoutStep step : steps) {
            result.append(step.getName()).append('+');
        }
        result = new StringBuilder(result.substring(0, result.length() - 1) + ')');

        return result.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        List<WorkoutStepMesg> result = new ArrayList<>();

        for (WorkoutStep step : steps) {
            List<WorkoutStepMesg> workoutMesgs = step.generateWorkoutSteps();
            result.addAll(workoutMesgs);
        }
        int startIntervalIndex = result.getFirst().getMessageIndex();

        WorkoutStepMesg repeatStep = new WorkoutStepMesg();
        repeatStep.setIntensity(Intensity.INTERVAL);
        repeatStep.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        repeatStep.setDurationValue((long)startIntervalIndex);
        repeatStep.setTargetType(WktStepTarget.INVALID);
        repeatStep.setTargetValue((long)intervalCount);
        repeatStep.setMessageIndex(generateWorkoutStepIndex());
        repeatStep.setNotes(nameWithIntensity());
        result.add(repeatStep);

        return result;
    }
}
