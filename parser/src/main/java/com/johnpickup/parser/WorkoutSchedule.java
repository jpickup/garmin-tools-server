package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

/**
 * Class that contains all the elements required to build a series of workouts and schedule them
 */
@Getter
@EqualsAndHashCode
@ToString
public class WorkoutSchedule {
    private final Map<String, Workout> workouts = new HashMap<>();
    private final Map<String, Pace> paces = new HashMap<>();
    private final List<ScheduledWorkout> schedule = new ArrayList<>();
}
