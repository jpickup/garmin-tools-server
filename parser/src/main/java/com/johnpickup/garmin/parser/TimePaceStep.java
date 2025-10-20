package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TimePaceStep extends Step {
    private final Time time;
    private final Pace pace;

    public TimePaceStep(Time time, Pace pace) {
        super();
        this.time = time;
        this.pace = pace;
    }

    public TimePaceStep(StepIntensity stepIntensity, Time time, Pace pace) {
        super(stepIntensity);
        this.time = time;
        this.pace = pace;
    }

    @Override
    public String toString() {
        return time + "@" + pace + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
