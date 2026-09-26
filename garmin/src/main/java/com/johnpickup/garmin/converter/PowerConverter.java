package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.parser.Power;

/**
 * Interface that power converters must implement.
 * One converter will be implemented for each sub-type of Power and will emit a corresponding
 * instance of a Garmin PowerTarget
 */
public interface PowerConverter {
    PowerTarget convert(Power power);
}
