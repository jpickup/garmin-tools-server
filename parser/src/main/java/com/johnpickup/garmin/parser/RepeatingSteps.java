package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 03/01/2017.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class RepeatingSteps extends Step {
    private final List<Step> steps = new ArrayList<>();
    @Setter
    private int repetitions = 1;

    public RepeatingSteps(Step firstStep) {
        steps.add(firstStep);
    }

    public RepeatingSteps() {
    }

    public RepeatingSteps(List<Step> steps) {
        this.steps.addAll(steps);
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    @Override
    public String toString() {
        if (steps.isEmpty()) return "";
        StringBuilder result = null;
        for (Step step : steps) {
            if (result == null) {
                result = new StringBuilder("(" + step.toString());
            }
            else {
                result.append(" + ").append(step.toString());
            }
        }
        result.append(") * ").append(repetitions);

        return result.toString();
    }
}
