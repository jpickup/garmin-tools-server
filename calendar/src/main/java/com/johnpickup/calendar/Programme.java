package com.johnpickup.calendar;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Programme {
    private final Collection<PlannedWorkout> workouts = new ArrayList<>();
    public void addWorkout(PlannedWorkout workout) {
        workouts.add(workout);
    }
}
