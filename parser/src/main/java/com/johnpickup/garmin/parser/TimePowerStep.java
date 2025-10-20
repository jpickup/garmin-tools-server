package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TimePowerStep extends Step {
    private final Time time;
    private final Power power;

    public TimePowerStep(Time time, Power power) {
        super();
        this.time = time;
        this.power = power;
    }

    public TimePowerStep(StepIntensity stepIntensity, Time time, Power power) {
        super(stepIntensity);
        this.time = time;
        this.power = power;
    }

    @Override
    public String toString() {
        return time + "@" + power + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
