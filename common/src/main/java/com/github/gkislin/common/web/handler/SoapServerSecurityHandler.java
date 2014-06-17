package com.github.gkislin.common.web.handler;

import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.util.Util;
import com.github.gkislin.common.web.ServletUtil;
import com.sun.xml.ws.api.handler.MessageHandlerContext;

import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

/**
 * User: gkislin
 * Date: 03.06.2014
 */
abstract public class SoapServerSecurityHandler extends SoapBaseHandler {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(SoapServerSecurityHandler.class);
    private String authHeader;

    public SoapServerSecurityHandler(String authHeader) {
        this.authHeader = authHeader;
    }

    public SoapServerSecurityHandler(String user, String password) {
        this.authHeader = ServletUtil.encodeBasicAuthHeader(user, password);
    }

    @Override
    public boolean handleMessage(MessageHandlerContext ctx) {
        if (!isOutbound(ctx)) {
            Map<String, List<String>> headers = (Map<String, List<String>>) ctx.get(MessageContext.HTTP_REQUEST_HEADERS);
            List<String> autHeader = headers.get(ServletUtil.AUTHORIZATION);
            int code = ServletUtil.getResponseCode((Util.isNotEmpty(autHeader) ? autHeader.get(0) : null), authHeader);
            if (code != 0) {
                ctx.put(MessageContext.HTTP_RESPONSE_CODE, code);
                throw new SecurityException();
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        LOGGER.error("Error when authorization");
        return false;
    }
}
