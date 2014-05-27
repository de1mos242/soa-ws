package com.github.gkislin.common.xml;


import com.github.gkislin.common.xml.JaxbParser;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.io.Reader;

/**
 * User: GKislin
 * Date: 17.06.2010
 * Time: 10:51:08
 */
public class JaxbSingleParser<T> extends JaxbParser {

    public JaxbSingleParser(Class<T> classToBeBound) {
        super(classToBeBound);
    }

    @Override
    public T unmarshal(InputStream is) throws JAXBException {
        return (T) super.unmarshal(is);
    }

    @Override
    public T unmarshal(Reader reader) throws JAXBException {
        return (T) super.unmarshal(reader);
    }

    @Override
    public T unmarshal(String str) throws JAXBException {
        return (T) super.unmarshal(str);
    }

    @Override
    public T unmarshalElement(Reader reader) throws JAXBException {
        return (T) super.unmarshalElement(reader);
    }

    @Override
    public T unmarshalElement(String str) throws JAXBException {
        return (T) super.unmarshalElement(str);
    }
}
