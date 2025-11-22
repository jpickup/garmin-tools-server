package com.johnpickup.garmintools.convert;

import com.johnpickup.calendar.Schedule;
import com.johnpickup.calendar.ScheduleIcalWriter;
import com.johnpickup.calendar.WorkoutScheduleConverter;
import com.johnpickup.garmin.parser.WorkoutSchedule;
import com.johnpickup.workout.excel.ExcelWorkoutScheduleReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;

@RequiredArgsConstructor
@Slf4j
public class ExcelToIcal implements ExcelConverter {
    private final ExcelWorkoutScheduleReader excelWorkoutScheduleReader;

    public byte[] convertXls(InputStream excelInputStream) throws Exception {
        WorkoutSchedule workoutSchedule = excelWorkoutScheduleReader.readXlsStream(excelInputStream);
        return convertWorkout(workoutSchedule);
    }

    public byte[] convertXlsx(InputStream excelInputStream) throws Exception {
        WorkoutSchedule workoutSchedule = excelWorkoutScheduleReader.readXlsxStream(excelInputStream);
        return convertWorkout(workoutSchedule);
    }

    private byte[] convertWorkout(WorkoutSchedule workoutSchedule) throws Exception {
        log.info("Converting workout schedule");
        Schedule schedule = new WorkoutScheduleConverter().convert(workoutSchedule);
        File tempDirectory = FileUtils.getTempDirectory();
        File iCalFile = new File(tempDirectory, "schedule.ics");

        log.info("Writing iCal schedule to {}", iCalFile);
        ScheduleIcalWriter icalWriter = new ScheduleIcalWriter(iCalFile);
        icalWriter.write(schedule);

        byte[] outputBytes = Files.readAllBytes(iCalFile.toPath());
        boolean deleted = iCalFile.delete();
        if (!deleted) {
            log.warn("Failed to delete {}", iCalFile);
        }
        return outputBytes;
    }
}
