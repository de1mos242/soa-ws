package com.github.gkislin.web;

import com.github.gkislin.common.web.BasicAuthFilter;
import com.github.gkislin.mail.MailWSClient;

/**
 * User: gkislin
 * Date: 17.06.2014
 */
public class RestFilter extends BasicAuthFilter {
    public RestFilter() {
        setAuthHeader(MailWSClient.getAuthHeader());
    }
}
