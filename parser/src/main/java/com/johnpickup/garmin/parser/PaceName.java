package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 07/01/2017.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class PaceName implements Pace {
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
