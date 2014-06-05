package com.github.gkislin.mail;

import com.github.gkislin.common.ExceptionType;
import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.StateException;
import com.github.gkislin.common.util.Util;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * User: gkislin
 * Date: 12.12.12
 */
public class MailSender {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(MailSender.class);


    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body, List<MailAttach> attachments) throws StateException {
        LOGGER.info("Send mail to '" + to + "' cc '" + cc + "' subject '" + subject + (LOGGER.isDebug() ? "\nbody=" + body : ""));
        try {
            HtmlEmail email = MailConfig.get().createEmail();
            email.setSubject(subject);
            email.setHtmlMsg(body);
            if (Util.isNotEmpty(to)) {
                for (Addressee addressee : to) {
                    email.addTo(addressee.getEmail(), addressee.getName());
                }
            }
            if (Util.isNotEmpty(cc)) {
                for (Addressee addressee : cc) {
                    email.addCc(addressee.getEmail(), addressee.getName());
                }
            }
            if (Util.isNotEmpty(attachments)) {
                for (MailAttach ma : attachments) {
                    email.attach(ma.getDataSource(), MailConfig.get().encodeWord(ma.getName()), MailConfig.get().encodeWord(ma.getDescription()));
                }
            }
            email.send();
        } catch (Exception e) {
            throw LOGGER.getStateException(e.toString(), ExceptionType.EMAIL, e);
        }
    }
}

