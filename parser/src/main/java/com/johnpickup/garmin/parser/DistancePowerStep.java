package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DistancePowerStep extends Step {
    private final Distance distance;
    private final Power power;

    public DistancePowerStep(Distance distance, Power power) {
        super();
        this.distance = distance;
        this.power = power;
    }

    public DistancePowerStep(StepIntensity stepIntensity, Distance distance, Power power) {
        super(stepIntensity);
        this.distance = distance;
        this.power = power;
    }
    @Override
    public String toString() {
        return distance + "@" + power + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
