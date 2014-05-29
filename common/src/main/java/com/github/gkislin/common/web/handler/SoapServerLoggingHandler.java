package com.github.gkislin.common.web.handler;

import com.github.gkislin.common.LoggingLevel;

public class SoapServerLoggingHandler extends SoapLoggingHandler {

    public SoapServerLoggingHandler() {
        super(LoggingLevel.DEBUG);
    }

    @Override
    protected boolean isRequest(boolean isOutbound) {
        return !isOutbound;
    }
}