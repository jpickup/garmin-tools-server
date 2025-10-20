package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TimeStep extends Step {
    private final Time time;

    public TimeStep(Time time) {
        super();
        this.time = time;
    }

    public TimeStep(StepIntensity stepIntensity, Time time) {
        super(stepIntensity);
        this.time = time;
    }

    @Override
    public String toString() {
        return time.toString() + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
