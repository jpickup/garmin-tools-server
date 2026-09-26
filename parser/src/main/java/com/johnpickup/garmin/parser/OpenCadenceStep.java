package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OpenCadenceStep extends Step {
    private final Cadence cadence;

    public OpenCadenceStep(Cadence cadence) {
        super();
        this.cadence = cadence;
    }
    public OpenCadenceStep(StepIntensity stepIntensity, Cadence cadence) {
        super(stepIntensity);
        this.cadence = cadence;
    }
}
