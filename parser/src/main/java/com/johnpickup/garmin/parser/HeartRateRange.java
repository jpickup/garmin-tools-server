package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class HeartRateRange implements HeartRate {
    private final int minimum;
    private final int maximum;
    private final HeartRateUnit unit;

    @Override
    public String toString() {
        return String.format("%s-%s%s", minimum, maximum, unit);
    }
}
