package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.parser.DistanceStep;
import com.johnpickup.garmin.parser.OpenStep;
import com.johnpickup.garmin.parser.RepeatingSteps;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimeStep;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that given a type of workout step returns an instance of the appropriate converter.
 * After the refactoring there are only 4 step types: DistanceStep, TimeStep, OpenStep, RepeatingSteps.
 * Target conversion is handled inside each step converter via TargetConverterFactory.
 */
public class StepConverterFactory {
    private static StepConverterFactory instance;
    private final Map<Class, StepConverter> converters = new HashMap<>();

    private StepConverterFactory() {
        register(new DistanceStepConverter(), DistanceStep.class);
        register(new TimeStepConverter(), TimeStep.class);
        register(new OpenStepConverter(), OpenStep.class);
        register(new RepeatingStepsConverter(), RepeatingSteps.class);
    }

    public static StepConverterFactory getInstance() {
        if (instance == null) {
            instance = new StepConverterFactory();
        }
        return instance;
    }

    private void register(StepConverter converter, Class stepClass) {
        converters.put(stepClass, converter);
    }

    public StepConverter createFor(Step step) {
        return converters.get(step.getClass());
    }
}
