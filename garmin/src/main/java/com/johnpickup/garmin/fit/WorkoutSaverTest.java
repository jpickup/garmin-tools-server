package com.johnpickup.garmin.fit;

import com.garmin.fit.Intensity;
import com.garmin.fit.Sport;
import com.johnpickup.garmin.converter.WorkoutConverter;
import com.johnpickup.garmin.fit.schedule.ScheduledWorkout;
import com.johnpickup.garmin.fit.schedule.TrainingSchedule;
import com.johnpickup.garmin.fit.workout.*;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.DistanceUnit;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.common.unit.PaceUnit;
import com.johnpickup.workout.parser.WorkoutTextParser;

import java.time.LocalDate;
import java.util.Collections;

/**
 * Created by john on 12/01/2017.
 */
public class WorkoutSaverTest {
    public static void main(String[] args) {
        WorkoutSaver saver = new WorkoutSaver();
        WorkoutTextParser parser = new WorkoutTextParser();
        WorkoutConverter converter = new WorkoutConverter();

        com.johnpickup.garmin.parser.Workout twoMile = parser.parse("2mi");
        Workout garminTwoMileWorkout = converter.convert(twoMile);
        saver.save(garminTwoMileWorkout, "parsed_2mi.fit");

        com.johnpickup.garmin.parser.Workout interval = parser.parse("1mi + (1mi@06:00-07:00/mi + 400m) * 4 + 1mi");
        Workout garminInterval = converter.convert(interval);
        saver.save(garminInterval, "parsed_interval.fit");

        WorkoutStep testDistance = new DistanceWorkoutStep(Intensity.ACTIVE, new Distance(2, DistanceUnit.MILE));
        Workout testDistanceWorkout = new Workout(Sport.RUNNING, null, Collections.singletonList(testDistance));
        saver.save(testDistanceWorkout, "testDist.fit");

        WorkoutStep testPace = new DistanceWorkoutStep(Intensity.ACTIVE, new Distance(2, DistanceUnit.MILE), new PaceTarget(null, 5, 6, PaceUnit.MIN_PER_MILE));
        Workout testPaceWorkout = new Workout(Sport.RUNNING, null, Collections.singletonList(testPace));
        saver.save(testPaceWorkout, "testPace.fit");

        TrainingSchedule trainingSchedule = new TrainingSchedule();
        trainingSchedule.addScheduledWorkout(new ScheduledWorkout(testDistanceWorkout, LocalDate.now()));
        trainingSchedule.addScheduledWorkout(new ScheduledWorkout(testPaceWorkout, LocalDate.now().plusDays(1)));
        //trainingSchedule.addScheduledWorkout(new ScheduledWorkout(testInterval, new Date(2017,1,3)));
        saver.save(trainingSchedule, "testSchedule.fit");
    }
}
