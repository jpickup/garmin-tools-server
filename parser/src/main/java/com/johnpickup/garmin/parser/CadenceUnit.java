package com.johnpickup.garmin.parser;

public enum CadenceUnit {
    RPM;

    @Override
    public String toString() {
        return switch (this) {
            case RPM -> "rpm";
        };
    }
}
