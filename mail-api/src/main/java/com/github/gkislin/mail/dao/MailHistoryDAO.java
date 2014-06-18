package com.github.gkislin.mail.dao;

import com.github.gkislin.common.sql.Sql;
import com.github.gkislin.mail.Addressee;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.09.13
 */
public class MailHistoryDAO {

    private static final BeanListHandler<MailHist> MAIL_HIST_HANDLER = new BeanListHandler<>(MailHist.class);
    private static final BeanHandler<MailHist> MAIL_HANDLER = new BeanHandler<>(MailHist.class);

    public static void save(List<Addressee> to, List<Addressee> cc, String subject, String body, String state) throws SQLException {
        Sql.update("INSERT INTO mail_hist(list_to,  list_cc,  subject,  body,  state,  date)" +
                " VALUES (?,?,?,?,?,now())", StringUtils.join(to, ", "), StringUtils.join(cc, ", "), subject, body, state);
    }

    public static List<MailHist> getAll() {
        return Sql.query("SELECT id, list_to as listTo, list_cc as listCc,subject,state,date FROM mail_hist ORDER BY date DESC", MAIL_HIST_HANDLER);
    }

    public static MailHist get(int id) {
        return Sql.query("SELECT id,list_to as listTo,list_cc as listCc,subject,state,date,body FROM mail_hist where id=?", MAIL_HANDLER, id);
    }
}
