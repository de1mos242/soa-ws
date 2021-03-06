package com.github.gkislin.common.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author GKislin
 *         Date: 18.11.2008
 */
public class JaxbUnmarshaller {
    private Unmarshaller unmarshaller;

    public JaxbUnmarshaller(JAXBContext ctx) throws JAXBException {
        unmarshaller = ctx.createUnmarshaller();
    }

    public synchronized void setSchema(Schema schema) {
        unmarshaller.setSchema(schema);
    }

    public synchronized Object unmarshal(InputStream is) throws JAXBException {
        return unmarshaller.unmarshal(is);
    }

    public synchronized Object unmarshal(Reader reader) throws JAXBException {
        return unmarshaller.unmarshal(reader);
    }

    public Object unmarshal(String str) throws JAXBException {
        return unmarshal(new StringReader(str));
    }

    public Object unmarshalElement(Reader reader) throws JAXBException {
        JAXBElement e = (JAXBElement) unmarshal(reader);
        return e.getValue();
    }

    public Object unmarshalElement(String str) throws JAXBException {
        return unmarshalElement(new StringReader(str));
    }

}
