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
public class Distance {
    private final double quantity;
    private final DistanceUnit unit;

    @Override
    public String toString() {
        return Double.toString(quantity) +  unit;
    }
}
