package com.johnpickup.gpx;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GpxReader {
    public GpxType readGpxFile(File file) throws JAXBException, FileNotFoundException {
        return readGpxStream(new FileInputStream(file));
    }

    public GpxType readGpxStream(InputStream stream) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("com.johnpickup.gpx");
        Unmarshaller um = jc.createUnmarshaller();
        return (GpxType) JAXBIntrospector.getValue(um.unmarshal(stream));
    }
}