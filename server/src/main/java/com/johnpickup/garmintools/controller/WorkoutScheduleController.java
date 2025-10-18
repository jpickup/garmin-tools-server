package com.johnpickup.garmintools.controller;


import com.johnpickup.garmintools.convert.ExcelToFitZip;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/schedule")
@Slf4j
public class WorkoutScheduleController {
    @Autowired
    ExcelToFitZip excelToFitZip;

    @PostMapping
    public ResponseEntity<byte[]> convertExcelToFitZip(@RequestPart MultipartFile excelFile) {
        log.info("Convert Excel file: {} ({})", excelFile.getOriginalFilename(), excelFile.getSize());

        String extension = FilenameUtils.getExtension(excelFile.getOriginalFilename());
        if (extension == null) {
            throw new RuntimeException("File has no filename");
        }

        try {
            log.info("Reading {}", excelFile.getOriginalFilename());
            String outputFilename = excelFile.getOriginalFilename().replace(".xlsx", ".zip").replace(".xls", ".zip");
            byte[] bytes = switch (extension.toLowerCase()) {
                case "xls" -> excelToFitZip.convertXls(excelFile.getInputStream());
                case "xlsx" -> excelToFitZip.convertXlsx(excelFile.getInputStream());
                default -> throw new RuntimeException("Unknown filetype " + extension);
            };

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + outputFilename)
                    .contentLength(bytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(bytes);

        } catch (IOException e) {
            log.error("Error processing Excel file upload of {}", excelFile.getOriginalFilename(), e);
            throw new RuntimeException("File is not a valid training schedule");
        }
    }
}
