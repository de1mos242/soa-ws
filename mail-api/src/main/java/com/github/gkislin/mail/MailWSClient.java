package com.github.gkislin.mail;


import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.LoggingLevel;
import com.github.gkislin.common.StateException;
import com.github.gkislin.common.io.ReadableFile;
import com.github.gkislin.common.util.Util;
import com.github.gkislin.common.web.handler.SoapClientLoggingHandler;
import com.github.gkislin.common.web.WebStateException;
import org.apache.commons.lang.StringUtils;

import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: gkislin
 * Date: 06.08.12
 */
public class MailWSClient {

    //    static String mailWsdl;
    private static final Service SERVICE;
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(MailWSClient.class);
    private static String mailWsdl;
    static SoapClientLoggingHandler loggingHandler = new SoapClientLoggingHandler(LoggingLevel.DEBUG);

    static {
        QName qname = new QName("http://mail.gkislin.github.com/", "MailServiceImplService");
        URL wsdlDocumentLocation = ReadableFile.getResourceUrl("wsdl/mailService.wsdl");
        SERVICE = Service.create(wsdlDocumentLocation, qname);
    }

    public static void setHost(String host) {
        mailWsdl = host + "/mail/mailService?wsdl";
    }

    // Get from "Name <mail>" or "mail"
    public static void sendMail(String to, String cc, String subject, String body) throws StateException {
        sendMail(create(to), create(cc), subject, body);
    }

    public static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) throws StateException {
        LOGGER.info("Send mail to '" + to + "' cc '" + cc + "' subject '" + subject + (LOGGER.isDebug() ? "\nbody=" + body : ""));
        try {
            getPort().sendMail(to, cc, subject, body);
        } catch (WebStateException e) {
            throw LOGGER.getStateException(e);
        }
    }

    private static MailService getPort() {
        MailService port = SERVICE.getPort(MailService.class);

        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> requestContext = bp.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mailWsdl);

        // logging
        Binding binding = bp.getBinding();
        List<Handler> handlerList = binding.getHandlerChain();
        handlerList.add(loggingHandler);
        binding.setHandlerChain(handlerList);

        return port;
    }

    public static List<Addressee> create(String emails) {
        if (Util.isEmpty(emails)) {
            return Collections.emptyList();
        }

        // Question: http://stackoverflow.com/questions/6374050/string-split-not-on-regular-expression
        if (emails.contains(",")) {
//            emails.split(",");
            String[] array = StringUtils.split(emails, ',');

            List<Addressee> list = new ArrayList<>(array.length);
            for (String email : array) {
                list.add(new Addressee(email));
            }
            return list;
        } else {
            return Collections.singletonList(new Addressee(emails));
        }
    }
}
