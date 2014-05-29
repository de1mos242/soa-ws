package com.github.gkislin.mail;

import com.github.gkislin.common.xml.JaxbSingleParser;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class AddresseeTest {
    private static final JaxbSingleParser<Addressee> PARSER_ADR = new JaxbSingleParser<>(Addressee.class);
    private static final Addressee ADDRESSEE = new Addressee("Имя", "name@mail.ru");

    static {
        PARSER_ADR.setSchema(AddresseeTest.class.getResource("/Addressee.xsd"));
    }

    @Test
    public void testMarshalUnmarshall() throws Exception {
        String str = PARSER_ADR.marshal(ADDRESSEE);
        PARSER_ADR.validate(str);

        String sampleAddressee = IOUtils.toString(AddresseeTest.class.getResource("/Addressee.xml"), UTF_8);
        assertEquals(sampleAddressee, str);

        Addressee a2 = PARSER_ADR.unmarshal(str);
        assertEquals(ADDRESSEE, a2);
    }

    @Test(expected = SAXParseException.class)
    public void testInvalid() throws Exception {
        PARSER_ADR.validate(IOUtils.toString(AddresseeTest.class.getResource("/InvalidAddressee.xml")));
    }
}
