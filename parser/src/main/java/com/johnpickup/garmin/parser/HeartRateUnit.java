package com.johnpickup.garmin.parser;

public enum HeartRateUnit {
    BPM;

    @Override
    public String toString() {
        return switch (this) {
            case BPM -> "bpm";
        };
    }
}
