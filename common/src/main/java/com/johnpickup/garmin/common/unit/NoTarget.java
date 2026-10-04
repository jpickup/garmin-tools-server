package com.johnpickup.garmin.common.unit;

/**
 * Singleton representing a step with no target (open intensity).
 */
public class NoTarget implements Target {
    public static final NoTarget INSTANCE = new NoTarget();

    private NoTarget() {}

    @Override
    public TargetType getTargetType() {
        return TargetType.NONE;
    }

    @Override
    public Long getGarminLow() {
        return 0L;
    }

    @Override
    public Long getGarminHigh() {
        return 0L;
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }

    @Override
    public String toString() {
        return "";
    }
}
