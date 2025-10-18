package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.schedule.TrainingSchedule;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.parser.Pace;
import com.johnpickup.parser.PaceName;
import com.johnpickup.parser.ScheduledWorkout;
import com.johnpickup.parser.Workout;
import com.johnpickup.parser.WorkoutSchedule;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 12/01/2017.
 */
public class WorkoutScheduleConverter {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WorkoutScheduleConverter.class);
    private final Map<String, PaceTarget> namedPaces = new HashMap<>();
    @Getter
    private final List<com.johnpickup.garmin.fit.workout.Workout> garminWorkouts = new ArrayList<>();
    @Getter
    private final TrainingSchedule trainingSchedule = new TrainingSchedule();
    private final Map<Workout, com.johnpickup.garmin.fit.workout.Workout> workoutMap = new HashMap<>();

    public WorkoutScheduleConverter() {
        PaceNameConverter namedPaceConverter = new PaceNameConverter(namedPaces);
        PaceConverterFactory.getInstance().register(namedPaceConverter, PaceName.class);
    }

    public void convert(WorkoutSchedule workoutSchedule) {
        init();
        log.debug("Converting paces");
        for (Map.Entry<String, Pace> namedPace : workoutSchedule.getPaces().entrySet()) {
            Pace pace = namedPace.getValue();
            log.debug("Pace: {}", pace);
            namedPaces.put(namedPace.getKey(), PaceConverterFactory.getInstance().getPaceConverter(pace).convert(pace));
        }
        log.debug("Done converting paces");

        WorkoutConverter workoutConverter = new WorkoutConverter();

        log.debug("Converting workouts");
        for (Map.Entry<String, Workout> workoutEntry : workoutSchedule.getWorkouts().entrySet()) {
            log.debug("Converting {} ", workoutEntry);
            com.johnpickup.garmin.fit.workout.Workout  garminWorkout = workoutConverter.convert(workoutEntry.getValue());
            garminWorkout.setName(workoutEntry.getKey());
            log.debug("Workout: {}", garminWorkout);
            garminWorkouts.add(garminWorkout);
            workoutMap.put(workoutEntry.getValue(), garminWorkout);
        }
        log.debug("Done converting workouts");

        log.debug("Converting schedules");
        for (ScheduledWorkout scheduledWorkout: workoutSchedule.getSchedule()) {
            Workout workout = scheduledWorkout.getWorkout();
            com.johnpickup.garmin.fit.workout.Workout garminWorkout = workoutMap.get(workout);
            com.johnpickup.garmin.fit.schedule.ScheduledWorkout garminScheduledWorkout =
                    new com.johnpickup.garmin.fit.schedule.ScheduledWorkout(garminWorkout, scheduledWorkout.getDate());
            log.debug("Workout schedule: {}", garminScheduledWorkout);
            trainingSchedule.addScheduledWorkout(garminScheduledWorkout);
        }
        log.debug("Done converting schedules");

    }

    private void init() {
        namedPaces.clear();
        garminWorkouts.clear();
        workoutMap.clear();
        trainingSchedule.clear();
    }
}
