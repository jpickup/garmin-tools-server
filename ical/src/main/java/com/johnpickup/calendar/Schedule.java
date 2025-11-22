package com.johnpickup.calendar;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Schedule {
    private final Collection<ScheduledWorkout> workouts = new ArrayList<>();

    public void addWorkout(ScheduledWorkout workout) {
        workouts.add(workout);
    }

    public void add(ScheduledWorkout workout) {
        workouts.add(workout);
    }

    @Override
    public String toString() {
        return "Schedule [workouts=" + workouts + "]";
    }

    public LocalDate getRaceDate() {
        LocalDate result = null;

        for (ScheduledWorkout workout : workouts) {
            if ((result == null) || (workout.getDate().isAfter(result))) {
                result = workout.getDate();
            }
        }
        return result;
    }

}
