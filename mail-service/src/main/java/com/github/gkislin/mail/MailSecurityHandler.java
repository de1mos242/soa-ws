package com.github.gkislin.mail;

import com.github.gkislin.common.web.handler.SoapServerSecurityHandler;

/**
 * User: gkislin
 * Date: 04.06.2014
 */
public class MailSecurityHandler extends SoapServerSecurityHandler {
    public MailSecurityHandler() {
        super(MailWSClient.getAuthHeader());
    }
}
