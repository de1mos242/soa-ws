package com.github.gkislin.mail;

import com.github.gkislin.common.converter.Converter;

import javax.activation.URLDataSource;
import java.net.URL;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public class AttachConverters {

    public static final Converter<UrlAttach, MailAttach> URL_ATTACH_CONVERTER = new Converter<UrlAttach, MailAttach>() {
        @Override
        public MailAttach convert(UrlAttach attach) throws Exception {
            return new MailAttach(attach.getName(), new URLDataSource(new URL(attach.getUrl())), attach.getDescription());
        }
    };

}
