package com.github.gkislin.mail;


import com.github.gkislin.common.StateException;
import com.github.gkislin.common.web.WebStateException;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.github.gkislin.mail.MailService")
//                                , wsdlLocation = "WEB-INF/wsdl/mailService.wsdl")
@HandlerChain(file = "mailWsHandlers.xml")
public class MailServiceImpl implements MailService {
//    private static String AUTH_HEADER = ServletUtil.encodeBasicAuthHeader(MailWSClient.user, MailWSClient.password);

//    @Resource
//    WebServiceContext wsContext;

    @Override
    public void sendMailUrl(List<Addressee> to, List<Addressee> cc, String subject, String body, List<UrlAttach> attachments) throws WebStateException {
//        MessageContext mctx = wsContext.getMessageContext();
//        Map headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
//        HttpServletRequest request = (HttpServletRequest) mctx.get(MessageContext.SERVLET_REQUEST);
//        HttpServletResponse response = (HttpServletResponse) mctx.get(MessageContext.SERVLET_RESPONSE);

//        ServletUtil.checkBasicAuth(request, response, AUTH_HEADER);

        try {
            MailSender.sendMail(to, cc, subject, body, attachments, AttachConverters.URL_ATTACH_CONVERTER);
        } catch (StateException e) {
            throw new WebStateException(e.getMessage(), e.getType());
        }
    }

    @Override
    public void sendMailMime(List<Addressee> to, List<Addressee> cc, String subject, String body, List<MimeAttach> attachments) throws WebStateException {
        try {
            MailSender.sendMail(to, cc, subject, body, attachments, AttachConverters.MIME_ATTACH_CONVERTER);
        } catch (StateException e) {
            throw new WebStateException(e.getMessage(), e.getType());
        }
    }
}