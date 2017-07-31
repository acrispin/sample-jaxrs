/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.sample.jaxrs.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author anton
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {
        System.out.println("Request filter");
        System.out.println("Headers: " + reqContext.getHeaders());
    }

    @Override
    public void filter(ContainerRequestContext reqContext, 
                       ContainerResponseContext resContext) throws IOException {
        System.out.println("Response Filter");
        System.out.println("Headers: " + resContext.getHeaders());
    }
    
}
