package com.github.gkislin.web.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: gkislin
 * Date: 27.09.13
 */

@Path("/")
public class MailRS {


    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String list(){
        return "Test";
    }

/*
    @GET
    @Path("/mail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MailHist mail(@PathParam("id") int id) {
        return MailHistoryDAO.get(id);
    }

    @GET
    @Path("/mails")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MailHist> mails() {
        return MailHistoryDAO.getAll();
    }
*/
}
