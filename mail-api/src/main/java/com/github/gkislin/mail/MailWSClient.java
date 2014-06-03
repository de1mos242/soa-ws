package com.github.gkislin.mail;


import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.LoggingLevel;
import com.github.gkislin.common.StateException;
import com.github.gkislin.common.util.Util;
import com.github.gkislin.common.web.WebStateException;
import com.github.gkislin.common.web.WsClient;
import com.github.gkislin.common.web.handler.SoapClientLoggingHandler;
import org.apache.commons.lang.StringUtils;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: gkislin
 * Date: 06.08.12
 */
public class MailWSClient {

    private static final WsClient<MailService> WS_CLIENT;
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(MailWSClient.class);
    private static final SoapClientLoggingHandler LOGGING_HANDLER = new SoapClientLoggingHandler(LoggingLevel.DEBUG);

    static String user = "user", password = "password";

    static {
        WS_CLIENT = new WsClient<>(
                "wsdl/mailService.wsdl",
                new QName("http://mail.gkislin.github.com/", "MailServiceImplService"),
                MailService.class);
    }

    public static void setHost(String host) {
        WS_CLIENT.setEndpointAddress(host + "/mail/mailService?wsdl");
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
        MailService port = WS_CLIENT.getPort();
        WsClient.setHandler(port, LOGGING_HANDLER);
        WsClient.setAuth(port, user, password);
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
