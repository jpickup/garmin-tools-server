package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class DistanceHeartRateStep extends Step {
    private final Distance distance;
    private final HeartRate heartRate;

    public DistanceHeartRateStep(Distance distance, HeartRate heartRate) {
        super();
        this.distance = distance;
        this.heartRate = heartRate;
    }

    public DistanceHeartRateStep(StepIntensity stepIntensity, Distance distance, HeartRate heartRate) {
        super(stepIntensity);
        this.distance = distance;
        this.heartRate = heartRate;
    }
    @Override
    public String toString() {
        return distance + "@" + heartRate + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
