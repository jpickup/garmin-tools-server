package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TimeCadenceStep extends Step {
    private final Time time;
    private final Cadence cadence;

    public TimeCadenceStep(Time time, Cadence cadence) {
        super();
        this.time = time;
        this.cadence = cadence;
    }

    public TimeCadenceStep(StepIntensity stepIntensity, Time time, Cadence cadence) {
        super(stepIntensity);
        this.time = time;
        this.cadence = cadence;
    }

    @Override
    public String toString() {
        return time + "@" + cadence + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
