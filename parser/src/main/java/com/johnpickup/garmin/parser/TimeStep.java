package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A step that lasts a specific time, with an optional target.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TimeStep extends Step {
    private final Time time;
    private final Target target;

    public TimeStep(Time time) {
        super();
        this.time = time;
        this.target = NoTarget.INSTANCE;
    }

    public TimeStep(StepIntensity stepIntensity, Time time) {
        super(stepIntensity);
        this.time = time;
        this.target = NoTarget.INSTANCE;
    }

    public TimeStep(Time time, Target target) {
        super();
        this.time = time;
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    public TimeStep(StepIntensity stepIntensity, Time time, Target target) {
        super(stepIntensity);
        this.time = time;
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    @Override
    public String toString() {
        String t = target instanceof NoTarget ? "" : "@" + target;
        return time.toString() + t + (stepIntensity == null ? "" : ("|" + stepIntensity));
    }
}
