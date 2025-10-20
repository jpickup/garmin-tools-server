package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class DistanceStep extends Step {
    private final Distance distance;

    public DistanceStep(Distance distance) {
        super();
        this.distance = distance;
    }

    public DistanceStep(StepIntensity stepIntensity, Distance distance) {
        super(stepIntensity);
        this.distance = distance;
    }
    @Override
    public String toString() {
        return distance.toString() + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
