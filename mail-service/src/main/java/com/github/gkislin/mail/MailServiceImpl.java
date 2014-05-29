package com.github.gkislin.mail;


import com.github.gkislin.common.StateException;
import com.github.gkislin.common.web.WebStateException;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.github.gkislin.mail.MailService")
                                //, wsdlLocation = "WEB-INF/wsdl/mailService.wsdl")
public class MailServiceImpl implements MailService {

    @Override
    public void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) throws WebStateException {
        try {
            MailSender.sendMail(to, cc, subject, body);
        } catch (StateException e) {
            throw new WebStateException(e.getMessage(), e.getType());
        }
    }
}