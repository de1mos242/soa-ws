package com.github.gkislin.mail;


import com.github.gkislin.common.StateException;
import com.github.gkislin.common.web.WebStateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
//@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, use = SOAPBinding.Use.LITERAL)
public interface MailService {

    /**
     * @throws StateException
     */
    @WebMethod
    public void sendMail(
            @WebParam(name = "to") List<Addressee> to,
            @WebParam(name = "cc") List<Addressee> cc,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body) throws WebStateException;
}