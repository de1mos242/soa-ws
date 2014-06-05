package com.github.gkislin.mail;

import org.junit.Test;

import java.util.Collections;

/**
 * User: gkislin
 * Date: 28.01.14
 */
public class MailWSClientIT {
    @Test
    public void testSendMail() throws Exception {
//        MailWSClient.setHost("http://localhost:8080");
        MailWSClient.sendMailUrl(MailWSClient.create("Имя <name@yandex.ru>"), null, "Русский текст", "<h2>Боди</h2>", Collections.singletonList(
                new UrlAttach("Имя.png", "file:///d:/doc/Картинка.png")));
    }
}
