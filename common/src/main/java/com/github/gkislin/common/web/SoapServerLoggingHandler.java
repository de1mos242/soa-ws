package com.github.gkislin.common.web;

import com.github.gkislin.common.LoggingLevel;

public class SoapServerLoggingHandler extends SoapLoggingHandler {

    public SoapServerLoggingHandler(LoggingLevel loggingLevel) {
        super(loggingLevel);
    }

    @Override
    protected boolean isRequest(boolean isOutbound) {
        return !isOutbound;
    }
}