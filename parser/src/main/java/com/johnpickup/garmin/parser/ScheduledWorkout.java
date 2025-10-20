package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Created by john on 11/01/2017.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class ScheduledWorkout {
    private final LocalDate date;
    private final Workout workout;
    private final String name;
    private final String description;

    public String toString() {
        return "ScheduledWorkout(date=" + this.getDate() + ", workout=" + this.getWorkout() + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }
}
