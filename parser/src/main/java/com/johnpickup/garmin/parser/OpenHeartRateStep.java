package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class OpenHeartRateStep extends Step {
    private final HeartRate heartRate;

    public OpenHeartRateStep(HeartRate heartRate) {
        super();
        this.heartRate = heartRate;
    }

    public OpenHeartRateStep(StepIntensity stepIntensity, HeartRate heartRate) {
        super(stepIntensity);
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return "Open@" + heartRate + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
