package com.johnpickup.calendar;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PlannedWorkout {
    private final String name;
    private final String description;
    private final long offset;
}
