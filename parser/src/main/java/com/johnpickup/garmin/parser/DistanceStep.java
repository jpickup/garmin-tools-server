package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A step that lasts a specific distance, with an optional target.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class DistanceStep extends Step {
    private final Distance distance;
    private final Target target;

    public DistanceStep(Distance distance) {
        super();
        this.distance = distance;
        this.target = NoTarget.INSTANCE;
    }

    public DistanceStep(StepIntensity stepIntensity, Distance distance) {
        super(stepIntensity);
        this.distance = distance;
        this.target = NoTarget.INSTANCE;
    }

    public DistanceStep(Distance distance, Target target) {
        super();
        this.distance = distance;
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    public DistanceStep(StepIntensity stepIntensity, Distance distance, Target target) {
        super(stepIntensity);
        this.distance = distance;
        this.target = target != null ? target : NoTarget.INSTANCE;
    }

    @Override
    public String toString() {
        String t = target instanceof NoTarget ? "" : "@" + target;
        return distance.toString() + t + (stepIntensity == null ? "" : ("|" + stepIntensity));
    }
}
