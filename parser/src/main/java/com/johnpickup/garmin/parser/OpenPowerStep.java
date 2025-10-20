package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OpenPowerStep extends Step {
    private final Power power;

    public OpenPowerStep(Power power) {
        super();
        this.power = power;
    }
    public OpenPowerStep(StepIntensity stepIntensity, Power power) {
        super(stepIntensity);
        this.power = power;
    }
}
