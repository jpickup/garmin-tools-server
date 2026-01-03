package com.johnpickup.garmintools.controller;

import com.johnpickup.garmintools.convert.GpxToFit;
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

@RestController
@RequestMapping("/api/route")
@Slf4j
public class RouteController {
    @Autowired
    GpxToFit gpxToFit;

    @PostMapping("/gpx")
    public ResponseEntity<byte[]> convertGpxToFitZip(@RequestPart MultipartFile file) {
        log.info("Convert GPX file: {} ({})", file.getOriginalFilename(), file.getSize());

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null) {
            throw new RuntimeException("File has no extension");
        }
        if (!extension.equals("gpx")) {
            throw new RuntimeException("File is not a GPX");
        }

        try {
            log.info("Reading {}", file.getOriginalFilename());
            String outputFilename = file.getOriginalFilename()
                    .replace(".gpx", ".fit");
            byte[] bytes = gpxToFit.convertGpx(file.getInputStream());
            log.info("Response file: {} ({} bytes)", outputFilename, bytes.length);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + outputFilename)
                    //.header("Access-Control-Allow-Origin", "*")       // already in WebConfig
                    .header("Access-Control-Expose-Headers", "*")
                    .header("Access-Control-Allow-Headers", "*")
                    .contentLength(bytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(bytes);
        } catch (Exception e) {
            log.error("Error processing GPX file upload of {}", file.getOriginalFilename(), e);
            throw new RuntimeException("File is not a valid GPX");
        }
    }
}
