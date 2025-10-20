package com.johnpickup.garmin.parser;

import lombok.Getter;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;

/**
 * Created by john on 16/01/2017.
 */
@Getter
public class AntlrErrorHandler extends DefaultErrorStrategy  {
    private boolean hadError = false;
    private String errorMessage = null;

    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        hadError = true;
        errorMessage = e.getMessage();
    }
}
