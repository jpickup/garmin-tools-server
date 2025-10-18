package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.common.unit.ZonePowerTarget;
import com.johnpickup.parser.Power;
import com.johnpickup.parser.PowerZone;

public class ZonePowerConverter implements PowerConverter {
    @Override
    public PowerTarget convert(Power power) {
        PowerZone powerZone = (PowerZone) power;
        return new ZonePowerTarget(powerZone.getZoneNumber());
    }
}
