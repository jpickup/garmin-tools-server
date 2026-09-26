package com.johnpickup.garmin.common.unit;

import java.util.Objects;

public class Cadence {
    private final long value;
    private final CadenceUnit unit;

    public Cadence(long value, CadenceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public String toValueString() {
        return switch (unit) {
            case RPM -> String.format("%d", value);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cadence power = (Cadence) o;
        return value == power.value && unit == power.unit;
    }

    public Long toGarminCadence() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
}
