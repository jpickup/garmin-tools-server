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
public class PaceRange implements Pace {
    private final Time minimum;
    private final Time maximum;
    private final PaceUnit unit;

    @Override
    public String toString() {
        return String.format("%s-%s%s",maximum, minimum, unit);
    }
}
