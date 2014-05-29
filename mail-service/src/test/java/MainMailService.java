import com.github.gkislin.common.io.ReadableFile;
import com.github.gkislin.mail.MailServiceImpl;

import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Endpoint;
import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 28.05.2014
 */
public class MainMailService {

    public static void main(String[] args) {

//        Endpoint.publish("http://localhost:8080/mail/mailService",
//                new MailServiceImpl());

        Endpoint endpoint = Endpoint.create(new MailServiceImpl());
        List metadata = Arrays.asList(
                new StreamSource(ReadableFile.getResource("wsdl/mailService.wsdl")),
                new StreamSource(ReadableFile.getResource("wsdl/common.xsd")));

        endpoint.setMetadata(metadata);
        endpoint.publish("http://localhost:8080/mail/mailService");

    }
}
