package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by john on 03/01/2017.
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Workout {
    private final List<? extends Step> steps;
    private Sport sport;
    private Integer poolLength;

    @Override
    public String toString() {
        String result = null;
        for (Step step : steps) {
            result = result==null?step.toString():result + " + " + step.toString();
        }
        return result;
    }
}
