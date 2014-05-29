package com.github.gkislin.common.web;

import com.sun.xml.ws.api.handler.MessageHandler;
import com.sun.xml.ws.api.handler.MessageHandlerContext;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import java.util.Set;

/**
 * User: GKislin
 * Date: 03.08.2009
 */
public abstract class SoapBaseHandler implements MessageHandler<MessageHandlerContext> {

    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public void close(MessageContext context) {
    }

    protected static boolean isOutbound(MessageHandlerContext context) {
        return (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    }
}
