package com.github.gkislin.mail;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public class MimeAttach extends Attach {

    @XmlMimeType("application/octet-stream")
    public DataHandler dataHandler;

    public MimeAttach() {
    }

    public MimeAttach(String name, DataHandler dataHandler) {
        super(name, null);
        this.dataHandler = dataHandler;
    }
}
