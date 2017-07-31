/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.rest;

import com.anton.dev.sample.jaxrs.model.MyDate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author anton
 */
@Path("date/{dateString}")
public class DateResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRequestedDate(@PathParam("dateString") MyDate mydate) {
        return "Obtenido " + mydate.toString();
    }
}
