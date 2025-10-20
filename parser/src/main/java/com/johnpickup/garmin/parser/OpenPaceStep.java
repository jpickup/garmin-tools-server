package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class OpenPaceStep extends Step {
    private final Pace pace;

    public OpenPaceStep(Pace pace) {
        super();
        this.pace = pace;
    }

    public OpenPaceStep(StepIntensity stepIntensity, Pace pace) {
        super(stepIntensity);
        this.pace = pace;
    }
    @Override
    public String toString() {
        return "Open@" + pace + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
