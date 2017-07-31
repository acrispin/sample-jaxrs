/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author anton
 * http://localhost:8084/sample-jaxrs/rest/paramValue/param?query=queryValue
 */
@Path("{pathParam}/param")
public class MyResourceParam {

    @PathParam("pathParam") private String pathParam;
    @QueryParam("query") private String queryParam;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
            StringBuilder sb = new StringBuilder();
            sb.append("Metodo test rest. ")
              .append("Path param used: ").append(pathParam)
              .append(" and ")
              .append("Query param used: ").append(queryParam);
            return sb.toString();
    }
	
    /**
     *
     * @param otherPathParam
     * @param otherQueryParam
     * @return
     * http://localhost:8084/sample-jaxrs/rest/paramValue/param/otherValue?query=queryValue&otherQuery=12345
     * http://localhost:8084/sample-jaxrs/rest/paramValue/param/otherValue?query=queryValue
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{otherPathParam}")
    public String testMethod2(
        @PathParam("otherPathParam") String otherPathParam,
        @QueryParam("otherQuery") @DefaultValue("other") String otherQueryParam) {
            StringBuilder sb = new StringBuilder();
            sb.append("Metodo test rest. ")
              .append("Path param used: ").append(pathParam)
              .append(" and ")
              .append("Query param used: ").append(queryParam)
              .append(" and ")
              .append("othe path param used: ").append(otherPathParam)
              .append(" and ")
              .append("other query param used: ").append(otherQueryParam);
            return sb.toString();
    }
    
}
