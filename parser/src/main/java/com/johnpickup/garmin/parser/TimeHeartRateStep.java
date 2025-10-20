package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TimeHeartRateStep extends Step {
    private final Time time;
    private final HeartRate heartRate;

    public TimeHeartRateStep(Time time, HeartRate heartRate) {
        super();
        this.time = time;
        this.heartRate = heartRate;
    }

    public TimeHeartRateStep(StepIntensity stepIntensity, Time time, HeartRate heartRate) {
        super(stepIntensity);
        this.time = time;
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return time + "@" + heartRate + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
