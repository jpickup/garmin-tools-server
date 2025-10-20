package com.johnpickup.garmin.parser;

import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
public abstract class Step {
    public Step() {
        this.stepIntensity = null;
    }

    public Step(StepIntensity stepIntensity) {
        this.stepIntensity = stepIntensity;
    }

    protected final StepIntensity stepIntensity;
}
