package com.github.gkislin.mail;

import com.github.gkislin.common.ExceptionType;
import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.StateException;

import java.util.List;

/**
 * User: gkislin
 * Date: 12.12.12
 */
public class MailSender {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(MailSender.class);


    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) throws StateException {
        LOGGER.info("Send mail to '" + to + "' cc '" + cc + "' subject '" + subject + (LOGGER.isDebug() ? "\nbody=" + body : ""));
        throw LOGGER.getStateException("Тест", ExceptionType.EMAIL);
    }
}

