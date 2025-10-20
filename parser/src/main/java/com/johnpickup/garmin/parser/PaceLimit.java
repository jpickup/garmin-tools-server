package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class PaceLimit implements Pace {
    private final Time time;
    private final PaceUnit unit;

    @Override
    public String toString() {
        return String.format("%s%s", getTime(), getUnit());
    }
}
