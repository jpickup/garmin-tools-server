package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class PowerRange implements Power {
    private final int minimum;
    private final int maximum;
    private final PowerUnit unit;

    @Override
    public String toString() {
        return String.format("%s-%s%s", minimum, maximum, unit);
    }
}
