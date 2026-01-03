package com.johnpickup.garmintools.convert;

import com.johnpickup.garmin.fit.FitSaver;
import com.johnpickup.garmin.fit.converter.CourseConverter;
import com.johnpickup.garmin.fit.route.Course;
import com.johnpickup.gpx.GpxReader;
import com.johnpickup.gpx.GpxType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

@RequiredArgsConstructor
@Slf4j
public class GpxToFit {
    private final GpxReader gpxReader;
    private final CourseConverter courseConverter;

    public byte[] convertGpx(InputStream gpxInputStream) throws Exception {
        GpxType gpxType = gpxReader.readGpxStream(gpxInputStream);

        Course convertedCourse = courseConverter.convert(gpxType);
        log.info("Converted GPX '{}' to a course containing {} points", convertedCourse.getName(), convertedCourse.size());

        File tempDirectory = FileUtils.getTempDirectory();
        File outputFile = new File(tempDirectory, "route.fit");

        log.info("Saving route to {}", outputFile);
        FitSaver.save(convertedCourse, outputFile);

        byte[] outputBytes = Files.readAllBytes(outputFile.toPath());
        boolean deleted = outputFile.delete();
        if (!deleted) {
            log.warn("Failed to delete {}", outputFile);
        }
        return outputBytes;
    }
}
