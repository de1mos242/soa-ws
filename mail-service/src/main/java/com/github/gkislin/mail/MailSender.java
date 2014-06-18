package com.github.gkislin.mail;

import com.github.gkislin.common.ExceptionType;
import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.StateException;
import com.github.gkislin.common.converter.Converter;
import com.github.gkislin.common.converter.ConverterUtil;
import com.github.gkislin.common.util.Util;
import com.github.gkislin.mail.dao.MailHistoryDAO;
import org.apache.commons.mail.HtmlEmail;

import java.sql.SQLException;
import java.util.List;

/**
 * User: gkislin
 * Date: 12.12.12
 */
public class MailSender {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(MailSender.class);


    static <T extends Attach> void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body, List<T> attachments,
                                            Converter<T, MailAttach> converter) throws StateException {
        sendMail(to, cc, subject, body, ConverterUtil.convert(attachments, converter, ExceptionType.ATTACH));
    }

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
            MailHistoryDAO.save(to, cc, subject, body, "OK");

        } catch (SQLException se) {
            throw LOGGER.getStateException("Ошибка записи в историю", ExceptionType.DATA_BASE, se);

        } catch (Exception e) {
            StateException se = LOGGER.getStateException(ExceptionType.EMAIL, e);
            try {
                MailHistoryDAO.save(to, cc, subject, body, e.toString());
            } catch (SQLException sqle) {
                LOGGER.error(sqle);
            }
            throw se;
        }
    }
}

