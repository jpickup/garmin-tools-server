package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class DistancePaceStep extends Step {
    private final Distance distance;
    private final Pace pace;

    public DistancePaceStep(Distance distance, Pace pace) {
        super();
        this.distance = distance;
        this.pace = pace;
    }

    public DistancePaceStep(StepIntensity stepIntensity, Distance distance, Pace pace) {
        super(stepIntensity);
        this.distance = distance;
        this.pace = pace;
    }
    @Override
    public String toString() {
        return distance + "@" + pace  + (stepIntensity==null?"":("|"+stepIntensity));
    }
}
