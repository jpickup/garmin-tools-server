package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.fit.schedule.TrainingSchedule;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.PaceName;
import com.johnpickup.garmin.parser.ScheduledWorkout;
import com.johnpickup.garmin.parser.Workout;
import com.johnpickup.garmin.parser.WorkoutSchedule;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

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

    public void convert(WorkoutSchedule workoutSchedule, Integer maxSchedule) {
        init();
        log.debug("Converting paces");
        for (Map.Entry<String, Pace> namedPace : workoutSchedule.getPaces().entrySet()) {
            Pace pace = namedPace.getValue();
            log.debug("Pace: {}", pace);
            namedPaces.put(namedPace.getKey(), PaceConverterFactory.getInstance().getPaceConverter(pace).convert(pace));
        }
        log.debug("Done converting paces");

        WorkoutConverter workoutConverter = new WorkoutConverter();

        if (maxSchedule != null) {
            log.info("Limiting schedule to a maximum of {} distinct workouts", maxSchedule);
        }

        int maxScheduleInt = Optional.ofNullable(maxSchedule).orElse(Integer.MAX_VALUE);

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

        List<com.johnpickup.garmin.fit.workout.Workout> distinctScheduledGarminWorkouts = new ArrayList<>();
        log.debug("Converting schedules");
        for (ScheduledWorkout scheduledWorkout: workoutSchedule.getSchedule()) {
            Workout workout = scheduledWorkout.getWorkout();
            com.johnpickup.garmin.fit.workout.Workout garminWorkout = workoutMap.get(workout);
            if (scheduledWorkout.getDate().isBefore(LocalDate.now())) {
                log.info("Skipping {} scheduled for {} as it is before today",
                        garminWorkout.getName(),
                        scheduledWorkout.getDate());
            } else {
                if (!distinctScheduledGarminWorkouts.contains(garminWorkout)) {
                    if (distinctScheduledGarminWorkouts.size() >= maxScheduleInt) {
                        log.info("Skipping {} scheduled for {} as it exceeds the maximum number",
                                garminWorkout.getName(),
                                scheduledWorkout.getDate());
                        continue;
                    } else {
                        distinctScheduledGarminWorkouts.add(garminWorkout);
                    }
                }
                com.johnpickup.garmin.fit.schedule.ScheduledWorkout garminScheduledWorkout =
                        new com.johnpickup.garmin.fit.schedule.ScheduledWorkout(garminWorkout, scheduledWorkout.getDate());
                log.debug("Workout schedule: {}", garminScheduledWorkout);
                trainingSchedule.addScheduledWorkout(garminScheduledWorkout);
            }
        }
        if (garminWorkouts.size() > maxScheduleInt) {
            trimGarminWorkouts(maxScheduleInt, distinctScheduledGarminWorkouts);
        }
        log.debug("Done converting schedules");
    }

    private void trimGarminWorkouts(int maxWorkouts, List<com.johnpickup.garmin.fit.workout.Workout> scheduledGarminWorkouts) {
        // start with the scheduled workouts
        Set<com.johnpickup.garmin.fit.workout.Workout> limitedWorkouts = new HashSet<>(scheduledGarminWorkouts);

        // if there is still space add in any more
        for (com.johnpickup.garmin.fit.workout.Workout garminWorkout : garminWorkouts) {
            if (limitedWorkouts.size() < maxWorkouts) {
                limitedWorkouts.add(garminWorkout);
            }
        }
        garminWorkouts.clear();
        garminWorkouts.addAll(limitedWorkouts);
        log.info("Trimmed the number of workouts to {}", garminWorkouts.size());
    }

    private void init() {
        namedPaces.clear();
        garminWorkouts.clear();
        workoutMap.clear();
        trainingSchedule.clear();
    }
}
