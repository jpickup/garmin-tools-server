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
    public ResponseEntity<byte[]> convertExcelToFitZip(@RequestPart MultipartFile file) {
        log.info("Convert Excel file: {} ({})", file.getOriginalFilename(), file.getSize());

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null) {
            throw new RuntimeException("File has no filename");
        }

        try {
            log.info("Reading {}", file.getOriginalFilename());
            String outputFilename = file.getOriginalFilename().replace(".xlsx", ".zip").replace(".xls", ".zip");
            byte[] bytes = switch (extension.toLowerCase()) {
                case "xls" -> excelToFitZip.convertXls(file.getInputStream());
                case "xlsx" -> excelToFitZip.convertXlsx(file.getInputStream());
                default -> throw new RuntimeException("Unknown filetype " + extension);
            };
            log.info("Response file: {} ({} bytes)", outputFilename, bytes.length);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + outputFilename)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Expose-Headers", "*")
                    .header("Access-Control-Allow-Headers", "*")
                    .contentLength(bytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(bytes);

        } catch (IOException e) {
            log.error("Error processing Excel file upload of {}", file.getOriginalFilename(), e);
            throw new RuntimeException("File is not a valid training schedule");
        }
    }
}
