package com.github.gkislin.mail;


import com.github.gkislin.common.StateException;
import com.github.gkislin.common.web.WebStateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.util.List;

@WebService
//@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, use = SOAPBinding.Use.LITERAL)
public interface MailService {

    /**
     * @throws StateException
     */
    @WebMethod
    public void sendMailUrl(
            @WebParam(name = "to") List<Addressee> to,
            @WebParam(name = "cc") List<Addressee> cc,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body,
            @WebParam(name = "attachments") List<UrlAttach> attachments) throws WebStateException;

    @WebMethod
    public void sendMailMime(
            @WebParam(name = "to") List<Addressee> to,
            @WebParam(name = "cc") List<Addressee> cc,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "body") String body,
            @WebParam(name = "attachments") @XmlMimeType("application/octet-stream") List<MimeAttach> attachments) throws WebStateException;

}