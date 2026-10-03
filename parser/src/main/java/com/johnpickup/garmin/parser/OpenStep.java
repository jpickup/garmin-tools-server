package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A step with no fixed duration (ends on lap button press), with an optional target.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class OpenStep extends Step {
    private final Target target;

    public OpenStep() {
        super();
        this.target = NoTarget.INSTANCE;
    }

    public OpenStep(StepIntensity stepIntensity) {
        super(stepIntensity);
        this.target = NoTarget.INSTANCE;
    }

    public OpenStep(Target target) {
        super();
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    public OpenStep(StepIntensity stepIntensity, Target target) {
        super(stepIntensity);
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    @Override
    public String toString() {
        String t = target instanceof NoTarget ? "" : "@" + target;
        return "Open" + t + (stepIntensity == null ? "" : ("|" + stepIntensity));
    }
}
