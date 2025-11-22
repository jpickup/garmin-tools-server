package com.johnpickup.garmintools.convert;

import java.io.InputStream;

public interface ExcelConverter {
    byte[] convertXls(InputStream excelInputStream) throws Exception;
    byte[] convertXlsx(InputStream excelInputStream) throws Exception;
}
