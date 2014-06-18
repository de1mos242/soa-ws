package com.github.gkislin.common.web;


import com.github.gkislin.common.ExceptionType;

import javax.xml.ws.WebFault;

/**
 * User: gkislin
 * Date: 28.01.14
 */
@WebFault(name = "webStateException", targetNamespace = "http://web.common.gkislin.github.com/")

public class WebStateException extends Exception {
    private ExceptionType type;

    public WebStateException(String message, ExceptionType faultInfo) {
        super(message);
        this.type = faultInfo;
    }

    public WebStateException(String message, ExceptionType faultInfo, Throwable cause) {
        super(message, cause);
        this.type = faultInfo;
    }

    public ExceptionType getFaultInfo() {
        return type;
    }
}
