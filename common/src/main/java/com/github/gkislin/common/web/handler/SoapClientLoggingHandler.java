package com.github.gkislin.common.web.handler;


import com.github.gkislin.common.LoggingLevel;

public class SoapClientLoggingHandler extends SoapLoggingHandler {
    public SoapClientLoggingHandler(LoggingLevel loggingLevel) {
        super(loggingLevel);
    }

    @Override
    protected boolean isRequest(boolean isOutbound) {
        return isOutbound;
    }
}