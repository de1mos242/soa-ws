package com.github.gkislin.common.xml;

import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.io.ReadableFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Marshalling/Unmarshalling JAXB helper
 *
 * @author Grigory Kislin
 */
public class JaxbParser {
    protected static final LoggerWrapper LOGGER = LoggerWrapper.get(JaxbParser.class);

    protected JaxbMarshaller jaxbMarshaller;
    protected JaxbUnmarshaller jaxbUnmarshaller;
    protected Schema schema;

    static IllegalStateException getInitException(JAXBException e) {
        return LOGGER.getIllegalStateException("JAXBContext couldn't be initialized", e);
    }

    public JaxbParser(Class... classesToBeBound) {
        try {
            init(JAXBContext.newInstance(classesToBeBound));
        } catch (JAXBException e) {
            throw getInitException(e);
        }
    }

    public JaxbParser(String context) {
        try {
            init(JAXBContext.newInstance(context));
        } catch (JAXBException e) {
            throw getInitException(e);
        }
    }

    private void init(JAXBContext ctx) throws JAXBException {
        jaxbMarshaller = new JaxbMarshaller(ctx);
        jaxbUnmarshaller = new JaxbUnmarshaller(ctx);
    }

    public void setMarshallerProperty(String prop, Object value) {
        try {
            jaxbMarshaller.setProperty(prop, value);
        } catch (PropertyException e) {
            throw LOGGER.getIllegalStateException("Marshaller Property setting exception", e);
        }
    }

    public Object unmarshal(InputStream is) throws JAXBException {
        return jaxbUnmarshaller.unmarshal(is);
    }

    public Object unmarshal(Reader reader) throws JAXBException {
        return jaxbUnmarshaller.unmarshal(reader);
    }

    public Object unmarshal(String str) throws JAXBException {
        return jaxbUnmarshaller.unmarshal(str);
    }

    public Object unmarshalElement(Reader reader) throws JAXBException {
        return jaxbUnmarshaller.unmarshalElement(reader);
    }

    public Object unmarshalElement(String str) throws JAXBException {
        return jaxbUnmarshaller.unmarshalElement(str);
    }

    public synchronized String marshal(Object instance) throws JAXBException {
        return jaxbMarshaller.marshal(instance);
    }

    public void marshal(Object instance, Writer writer) throws JAXBException {
        jaxbMarshaller.marshal(instance, writer);
    }

    // Validation
    private static final SchemaFactory SCHEMA_FACTORY = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    public static synchronized Schema getSchemaFromString(String xsd) throws SAXException {
        return SCHEMA_FACTORY.newSchema(getSource(xsd));
    }

    public static synchronized Schema getSchemaFromUrl(URL url) throws SAXException {
        return SCHEMA_FACTORY.newSchema(url);
    }

    public static Source getSource(String data) {
        return new StreamSource(new StringReader(data));
    }

    public void setSchema(String filePath) throws SAXException {
        try {
            setSchema(new ReadableFile(filePath).toURI().toURL());
        } catch (MalformedURLException e) {
            throw LOGGER.getIllegalArgumentException("Schema path " + filePath + " is invalid", e);
        }
    }

    public void setSchema(URL schemaUrl) throws SAXException {
        schema = getSchemaFromUrl(schemaUrl);
        jaxbUnmarshaller.setSchema(schema);
        jaxbMarshaller.setSchema(schema);
    }

    public void validate(String str) throws IOException, SAXException {
        validate(new StringReader(str));
    }

    public void validate(Reader reader) throws IOException, SAXException {
        schema.newValidator().validate(new StreamSource(reader));
    }
}
