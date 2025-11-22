package com.johnpickup.calendar;

import java.io.FileInputStream;
import java.io.IOException;

import com.johnpickup.workout.excel.ExcelUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

@RequiredArgsConstructor
public class ProgrammeReader {

    private final String filename;
    private int nameIndex = -1;
    private int descriptionIndex = -1;
    private int offsetIndex = -1;

    public Programme read() throws IOException {
        Programme result = new Programme();
        FileInputStream inputFile = new FileInputStream(filename);
        Workbook wb = new HSSFWorkbook(inputFile);
        Sheet sheet = wb.getSheet("Schedule");
        int rowIdx = 0;
        for (Row row : sheet) {
            if (rowIdx++ == 0) {
                readHeader(row);
            } else {
                PlannedWorkout workout = readWorkout(row);
                if (workout != null) {
                    result.addWorkout(workout);
                }
            }
        }
        wb.close();
        return result;
    }

    private PlannedWorkout readWorkout(Row row) {
        String name = ExcelUtils.readStringValue(row, nameIndex);
        String description = ExcelUtils.readStringValue(row, descriptionIndex);
        Integer offset = ExcelUtils.readIntValue(row, offsetIndex);
        if (name == null || description == null || offset == null) return null;
        return new PlannedWorkout(name, description, offset);
    }

    private void readHeader(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Workout".equals(cell.getStringCellValue())) nameIndex = cell.getColumnIndex();
            if ("Description".equals(cell.getStringCellValue())) descriptionIndex = cell.getColumnIndex();
            if ("Offset".equals(cell.getStringCellValue())) offsetIndex = cell.getColumnIndex();
        }
    }
}
