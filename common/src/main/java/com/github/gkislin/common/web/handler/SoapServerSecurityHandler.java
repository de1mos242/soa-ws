package com.github.gkislin.common.web.handler;

import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.web.ServletUtil;
import com.sun.xml.ws.api.handler.MessageHandlerContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.MessageContext;

/**
 * User: gkislin
 * Date: 03.06.2014
 */
abstract public class SoapServerSecurityHandler extends SoapBaseHandler {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(SoapServerSecurityHandler.class);
    private String authHeader;

    public SoapServerSecurityHandler(String user, String password) {
        this.authHeader = ServletUtil.encodeBasicAuthHeader(user, password);
    }

    @Override
    public boolean handleMessage(MessageHandlerContext ctx) {
        if (!isOutbound(ctx)) {
            HttpServletRequest request = (HttpServletRequest) ctx.get(MessageContext.SERVLET_REQUEST);
            HttpServletResponse response = (HttpServletResponse) ctx.get(MessageContext.SERVLET_RESPONSE);
            ServletUtil.checkBasicAuth(request, response, authHeader);
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        LOGGER.error("Error when authorization");
        return false;
    }
}
