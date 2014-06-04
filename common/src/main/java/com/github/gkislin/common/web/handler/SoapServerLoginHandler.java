package com.github.gkislin.common.web.handler;

import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.config.RootConfig;
import com.github.gkislin.common.web.ServletUtil;
import com.sun.xml.ws.api.handler.MessageHandlerContext;

import javax.jws.soap.SOAPMessageHandler;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Unknown on 05.06.2014.
 */
public class SoapServerLoginHandler implements SOAPHandler<SOAPMessageContext> {

    private static final LoggerWrapper LOGGER = LoggerWrapper.get(SoapServerLoginHandler.class);
    public static final String AUTHORIZATION = "authorization";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("WE HERE");
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        LOGGER.info("check message: " + isRequest);
        System.out.println("WE still HERE " + isRequest);
        if (!isRequest) {
            System.out.println("check incoming request");
            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();
                Map<String, List<String>> httpHeaders =
                        (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
                System.out.println("headers: " + httpHeaders.toString());
                if (!httpHeaders.containsKey(AUTHORIZATION)) {
                    throw new SecurityException("Unauthorized");
                }
                if (httpHeaders.get(AUTHORIZATION).size() == 0) {
                    throw new SecurityException("Not authorized");
                }
                String user = RootConfig.getConf().getString("mail.client.user");
                String password = "xxx";
                String rightHeader = ServletUtil.encodeBasicAuthHeader(user, password);
                System.out.println("check: " + httpHeaders.get(AUTHORIZATION).get(0) + " vs " + rightHeader);
                if (!httpHeaders.get(AUTHORIZATION).get(0).equals(rightHeader)) {
                    throw new SecurityException("Forbidden");
                }
            }
            catch (SOAPException e) {
                LOGGER.error(e);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

}

