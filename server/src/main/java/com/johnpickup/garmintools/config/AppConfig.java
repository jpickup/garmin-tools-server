package com.johnpickup.garmintools.config;

import com.johnpickup.garmintools.convert.ExcelToFitZip;
import com.johnpickup.workout.excel.ExcelWorkoutScheduleReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    ExcelToFitZip excelToFitZip() {
        return new ExcelToFitZip(excelWorkoutScheduleReader());
    }

    @Bean
    ExcelWorkoutScheduleReader excelWorkoutScheduleReader() {
        return new ExcelWorkoutScheduleReader();
    }
}
