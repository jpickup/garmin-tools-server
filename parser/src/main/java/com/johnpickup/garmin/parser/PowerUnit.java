package com.johnpickup.garmin.parser;

public enum PowerUnit {
    WATTS;

    @Override
    public String toString() {
        return switch (this) {
            case WATTS -> "W";
        };
    }
}
