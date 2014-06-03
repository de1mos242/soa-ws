package com.github.gkislin.mail;

import org.junit.Test;

/**
 * User: gkislin
 * Date: 28.01.14
 */
public class MailWSClientIT {
    @Test
    public void testSendMail() throws Exception {
//        MailWSClient.setHost("http://localhost:8080");
        MailWSClient.sendMail("Григорий <gk@yandex.ru>, Учебный Центр <inf@levelp.ru>", null, "Русский текст", "<h2>Боди</h2>");
    }
}
