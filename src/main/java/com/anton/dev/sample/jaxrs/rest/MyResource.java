package com.anton.dev.sample.jaxrs.rest;

import java.util.Calendar;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        return "It works....";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("date")
    public Date testMethod2() {
        return Calendar.getInstance().getTime();
    }

    @GET
    @Produces("text/shortdate")
    @Path("shortdate")
    public Date testMethod3() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Depende del cliente que configure su header Accept, ejm
     * Accept text/shortdate - obtendra el date segun ShortDateMessageBodyWriter
     * Accept text/plain - obtendra el date segun DateMessageBodyWriter
     * @return
     */
    @GET
    @Produces(value = {MediaType.TEXT_PLAIN, "text/shortdate"})
    @Path("shortdate2")
    public Date testMethod4() {
        return Calendar.getInstance().getTime();
    }

}
