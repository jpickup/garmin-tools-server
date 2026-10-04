package com.johnpickup.garmin.common.unit;

import lombok.EqualsAndHashCode;

/**
 * Cadence target - a minimum and maximum cadence in rpm
 */
@EqualsAndHashCode(callSuper = false)
public class CustomCadenceTarget extends CadenceTarget {
    private final Cadence maxCadence;
    private final Cadence minCadence;

    public CustomCadenceTarget(long min, long max, CadenceUnit unit) {
        this.minCadence = new Cadence(min, unit);
        this.maxCadence = new Cadence(max, unit);
    }

    @Override
    public String toString() {
        return minCadence.toValueString() + "-" + maxCadence;
    }

    public Long getGarminLow() {
        if (minCadence.toGarminCadence() < maxCadence.toGarminCadence())
            return minCadence.toGarminCadence();
        else
            return maxCadence.toGarminCadence();
    }

    public Long getGarminHigh() {
        if (minCadence.toGarminCadence() < maxCadence.toGarminCadence())
            return maxCadence.toGarminCadence();
        else
            return minCadence.toGarminCadence();
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }
}
