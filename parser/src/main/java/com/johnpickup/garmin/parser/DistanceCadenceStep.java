package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DistanceCadenceStep extends Step {
    private final Distance distance;
    private final Cadence cadence;

    public DistanceCadenceStep(Distance distance, Cadence cadence) {
        super();
        this.distance = distance;
        this.cadence = cadence;
    }

    public DistanceCadenceStep(StepIntensity stepIntensity, Distance distance, Cadence cadence) {
        super(stepIntensity);
        this.distance = distance;
        this.cadence = cadence;
    }
    @Override
    public String toString() {
        return distance + "@" + cadence + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
