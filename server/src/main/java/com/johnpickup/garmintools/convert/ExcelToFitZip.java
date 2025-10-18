package com.johnpickup.garmintools.convert;

import com.johnpickup.garmin.converter.WorkoutScheduleConverter;
import com.johnpickup.garmin.fit.WorkoutSaver;
import com.johnpickup.garmin.fit.workout.Workout;
import com.johnpickup.parser.WorkoutSchedule;
import com.johnpickup.workout.excel.ExcelWorkoutScheduleReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@RequiredArgsConstructor
@Slf4j
public class ExcelToFitZip {
    private final ExcelWorkoutScheduleReader excelWorkoutScheduleReader;

    public byte[] convertXls(InputStream excelInputStream) throws IOException {
        WorkoutSchedule workoutSchedule = excelWorkoutScheduleReader.readXlsStream(excelInputStream);
        return convertWorkout(workoutSchedule);
    }

    public byte[] convertXlsx(InputStream excelInputStream) throws IOException {
        WorkoutSchedule workoutSchedule = excelWorkoutScheduleReader.readXlsxStream(excelInputStream);
        return convertWorkout(workoutSchedule);
    }

    private byte[] convertWorkout(WorkoutSchedule workoutSchedule) throws IOException {
        WorkoutScheduleConverter converter = new WorkoutScheduleConverter();
        WorkoutSaver workoutSaver = new WorkoutSaver();

        log.info("Converting workout schedule");
        converter.convert(workoutSchedule);

        ObjectMapper objectMapper = new ObjectMapper();
        if (log.isDebugEnabled()) {
            log.debug("{}", objectMapper.writeValueAsString(workoutSchedule));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(outputStream);
        File tempDirectory = FileUtils.getTempDirectory();
        List<File> temporaryFiles = new ArrayList<>();

        log.info("Saving workouts");
        for (Workout garminWorkout : converter.getGarminWorkouts()) {
            String workoutFilename = generateWorkoutFilename(garminWorkout);
            File workoutFile = new File(tempDirectory, workoutFilename);
            log.debug("Saving workout {} as {}", garminWorkout, workoutFile.getPath());
            workoutSaver.save(garminWorkout, workoutFile);
            temporaryFiles.add(workoutFile);
            log.info("Saved workout {} as {}", garminWorkout, workoutFile.getPath());
            addFileToZip(workoutFile, zipOut);
        }

        String scheduleFilename = "schedule.fit";
        File scheduleFile = new File(tempDirectory, scheduleFilename);
        log.info("Saving schedule as {}", scheduleFile.getPath());
        workoutSaver.save(converter.getTrainingSchedule(), scheduleFile);
        temporaryFiles.add(scheduleFile);
        log.info("Saved workout schedule as {}", scheduleFile.getPath());
        addFileToZip(scheduleFile, zipOut);
        zipOut.close();

        temporaryFiles.forEach(File::delete);

        return outputStream.toByteArray();
    }

    private void addFileToZip(File file, ZipOutputStream zipOut) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    private String generateWorkoutFilename(Workout garminWorkout) {
        String name = garminWorkout.getName();
        return name.trim().replaceAll(" ","").replaceAll("/","")
                .replaceAll("\\(","").replaceAll("\\)","")
                .replaceAll(":","").replaceAll("\\+","-")
                .replaceAll("\\*","x")+ ".fit";
    }
}
