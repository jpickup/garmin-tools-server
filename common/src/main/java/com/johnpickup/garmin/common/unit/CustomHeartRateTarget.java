package com.johnpickup.garmin.common.unit;

import lombok.EqualsAndHashCode;

/**
 * Heart Rate target - a minimum and maximum HR
 */
@EqualsAndHashCode(callSuper = false)
public class CustomHeartRateTarget extends HeartRateTarget {
    private final HeartRate maxHeartRate;
    private final HeartRate minHeartRate;

    public CustomHeartRateTarget(long min, long max, HeartRateUnit unit) {
        this.minHeartRate = new HeartRate(min, unit);
        this.maxHeartRate = new HeartRate(max, unit);
    }

    @Override
    public String toString() {
        return minHeartRate.toValueString() + "-" + maxHeartRate;
    }

    public Long getGarminLow() {
        if (minHeartRate.toGarminHeartRate() < maxHeartRate.toGarminHeartRate())
            return minHeartRate.toGarminHeartRate();
        else
            return maxHeartRate.toGarminHeartRate();
    }

    public Long getGarminHigh() {
        if (minHeartRate.toGarminHeartRate() < maxHeartRate.toGarminHeartRate())
            return maxHeartRate.toGarminHeartRate();
        else
            return minHeartRate.toGarminHeartRate();
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }
}
