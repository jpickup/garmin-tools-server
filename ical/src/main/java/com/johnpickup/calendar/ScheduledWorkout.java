package com.johnpickup.calendar;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ScheduledWorkout {
    private final String name;
    private final String description;
    private final LocalDate date;
}
